package com.example.project1.controller;

import com.example.project1.model.User;
import com.example.project1.model.Victim;
import com.example.project1.model.Volunteer;
import com.example.project1.repository.AdminRepository;
import com.example.project1.repository.UserRepository;
import com.example.project1.repository.VictimRepository;
import com.example.project1.repository.VolunteerRepository;
import com.example.project1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/")
public class UserController {

    private final UserService userService;
    private final VolunteerRepository volunteerRepository;
    private final VictimRepository victimRepository;
    private final AdminRepository adminRepository;
    private final UserRepository userRepository;

    @Autowired
    public UserController(UserService userService,
                          VolunteerRepository volunteerRepository,
                          VictimRepository victimRepository,
                          AdminRepository adminRepository,
                          UserRepository userRepository) {
        this.userService = userService;
        this.volunteerRepository = volunteerRepository;
        this.victimRepository = victimRepository;
        this.adminRepository = adminRepository;
        this.userRepository = userRepository;
    }

    // ---------- REGISTRATION ----------
    @GetMapping("register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if (userService.userExists(user.getUsername())) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }
        userService.saveUser(user);
        return "redirect:/role-selection?username=" + user.getUsername() + "&fromRegister=true";
    }

    // ---------- LOGIN ----------
    @GetMapping("login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            Model model) {
        User user = userService.findByUsername(username);

        if (user == null || !user.getPassword().equals(password)) {
            model.addAttribute("error", "Invalid username or password");
            return "login";
        }

        return "redirect:/role-selection?username=" + username;
    }

    // ---------- SHOW ROLE SELECTION PAGE ----------
    @GetMapping("role-selection")
    public String showRoleSelectionPage(@RequestParam(required = false) String username,
                                        @RequestParam(required = false) String fromRegister,
                                        Model model) {
        if (username == null || username.trim().isEmpty()) {
            return "redirect:/login";
        }
        model.addAttribute("username", username);
        model.addAttribute("fromRegister", "true".equals(fromRegister));
        User user = userService.findByUsername(username);
        model.addAttribute("user", user);
        return "role_selection";
    }

    // ---------- HANDLE ROLE SELECTION ----------
    @PostMapping("role-selection")
    public String handleRoleSelection(@RequestParam(required = false) String username,
                                      @RequestParam String role,
                                      @RequestParam(required = false, defaultValue = "false") boolean fromRegister) {

        if (username == null || username.trim().isEmpty()) {
            return "redirect:/login";
        }

        switch (role) {
            case "volunteer":
                return userService.isVolunteer(username)
                        ? "redirect:/volunteer/dashboard?username=" + username
                        : "redirect:/volunteer/login?username=" + username;

            case "admin":
                if (fromRegister) {
                    return "redirect:/admin-key?username=" + username;
                } else {
                    return userService.isAdmin(username)
                            ? "redirect:/admin/dashboard?username=" + username
                            : "redirect:/admin-auth?username=" + username;
                }

            case "victim":
                return userService.isVictim(username)
                        ? "redirect:/victim/dashboard?username=" + username
                        : "redirect:/victim/login?username=" + username;

            default:
                return "redirect:/role-selection?username=" + username;
        }

    }

    // ---------- SHOW ADMIN KEY PAGE ----------
    @GetMapping("admin-key")
    public String showAdminKey(@RequestParam("username") String username, Model model) {
        String adminKey = userService.getAdminKeyIfExists(username);
        model.addAttribute("adminKey", adminKey);
        model.addAttribute("username", username);
        return  "admin_key" ;
    }

    @PostMapping("admin-key")
    public String handleAdminKeySubmit(@RequestParam("username") String username,
                                       @RequestParam(value = "fromRegister", required = false) String fromRegister) {
        String adminKey = userService.getAdminKeyIfExists(username);

        if (adminKey != null) {
            return "redirect:/admin-auth?username=" + username;
        } else {
            return "redirect:/role-selection?username=" + username + "&fromRegister=true";
        }
    }

    @PostMapping("/update-profile")
    public String updateProfile(@RequestParam String username,
                                @RequestParam String email,
                                @RequestParam(required = false) String password,

                                @RequestParam(required = false) String fromRegister,
                                RedirectAttributes redirectAttributes) {

        User user = userService.findByUsername(username);
        if (user != null) {
            user.setEmail(email);
            if (password != null && !password.trim().isEmpty()) {
                user.setPassword(password);
            }

            userService.saveUser(user);

            redirectAttributes.addFlashAttribute("message", "Profile updated successfully.");
        }

        // Handle optional fromRegister in redirect
        String redirectUrl = "redirect:/role-selection?username=" + username;
        if ("true".equalsIgnoreCase(fromRegister)) {
            redirectUrl += "&fromRegister=true";
        }

        return redirectUrl;
    }

    // ---------- DELETE USER ----------
    @PostMapping("/delete-user")
    public String deleteUser(@RequestParam String username) {
        if (userService.isVolunteer(username)) {
            volunteerRepository.deleteByUsername(username);
        }
        if (userService.isVictim(username)) {
            victimRepository.deleteByUsername(username);
        }
        if (userService.isAdmin(username)) {
            adminRepository.deleteByUsername(username);
        }
        userRepository.deleteByUsername(username);
        return "redirect:/register";
    }

}
