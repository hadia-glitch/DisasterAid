/*package com.example.project1.controller;

import com.example.project1.model.Victim;
import com.example.project1.service.VictimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/victim")
public class VictimController {

    @Autowired
    private VictimService victimService;

    // Display the victim login form (which is actually the registration form)
    @GetMapping("/login")
    public String showRegistrationForm(Model model) {
        model.addAttribute("victim", new Victim());
        return "victim_form";  // Thymeleaf view name matches the file name 'victim_form.html'
    }

    // Handle the form submission and save victim details
    @PostMapping("/login")
    public String registerVictim(Victim victim, Model model) {
        try {
            // Save the victim details in the database using the service layer
            victimService.saveVictim(victim);

            // Add success message to model and redirect back to the form with success message
            model.addAttribute("success", true);
            return "redirect:/victim/dashboard?username=" + victim.getUsername();
        } catch (Exception e) {
            // Add error message to model if something goes wrong
            model.addAttribute("success", false);
            return "victim_form";  // Return to 'victim_form.html' on failure
        }
    }

    // Show the form pre-filled with existing victim's details for updating
   @GetMapping("/update")
    public String showUpdateVictimForm(@RequestParam("username") String username, Model model) {
        // Fetch victim data by username
        Victim victim = victimService.getVictimByUsername(username);
        if (victim != null) {
            model.addAttribute("victim", victim);  // Pre-fill the form
        } else {
            model.addAttribute("error", "Victim not found.");
        }
     //   return "update_victim";  // Return the update victim form
       return "redirect:/victim/update-victim?username=" + username;
    }

    // Handle the update form submission
    @PostMapping("/update")
    public String saveVictimUpdate(@ModelAttribute Victim victim, Model model) {
        try {
            victimService.saveVictim(victim);  // Save the updated victim details
            model.addAttribute("success", true);  // Success message
        } catch (Exception e) {
            model.addAttribute("success", false);  // Failure message
        }
        return "redirect:/victim/dashboard?username=" + victim.getUsername();  // Redirect to victim dashboard
    }
    // Show the victim dashboard
    @GetMapping("/dashboard")
    public String showVictimDashboard(@RequestParam("username") String username, Model model) {
        model.addAttribute("username", username);
        return "victim_dashboard";
    }

    // Placeholder: Submit request
    @GetMapping("/submit-request")
    public String submitRequest(@RequestParam("username") String username, Model model) {
        // Placeholder logic
        model.addAttribute("message", "Submit request page for " + username);
        return "placeholder_submit_request"; // Create this Thymeleaf file as a placeholder
    }


    @GetMapping("/updatedetails")
    public String showUpdatedVictimForm(@RequestParam(value = "username", required = false) String username, Model model) {
        if (username != null && !username.isEmpty()) {
            Victim victim = victimService.getVictimByUsername(username);
            if (victim != null) {
                model.addAttribute("victim", victim);
            } else {
                model.addAttribute("error", "Victim not found.");
                model.addAttribute("victim", new Victim()); // empty for form
            }
        } else {
            model.addAttribute("victim", new Victim()); // initial load
        }
        return "update_victim_details";
    }

    @PostMapping("/updatedetails")
    public String updateVictim(@ModelAttribute Victim victim, Model model) {
        try {
            victimService.saveVictim(victim);
            return "redirect:/victim/dashboard?username=" + victim.getUsername();
        } catch (Exception e) {
           // model.addAttribute("error", "Failed to update.");
            model.addAttribute("victim", victim);
            return "update_victim_details";
        }
    }
}

   /* // Redirect to victim update form
    @GetMapping("/victim/update")
    public String updateVictim(@RequestParam("username") String username) {
        return "redirect:/victim/update-victim?username=" + username;
    }*/
package com.example.project1.controller;

import com.example.project1.model.Victim;
import com.example.project1.repository.VictimRepository;
import com.example.project1.service.VictimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/victim")
public class VictimController {

    @Autowired
    private VictimRepository victimRepository;
    @Autowired
    private VictimService victimService;

    @GetMapping("/login")
    public String showRegistrationForm(Model model) {
        model.addAttribute("victim", new Victim());
        return "victim_form";
    }

    // Handle the form submission and save victim details
    @PostMapping("/login")
    public String registerVictim(Victim victim, Model model) {
        try {
            // Save the victim details in the database using the service layer
            victimService.saveVictim(victim);

            // Add success message to model and redirect back to the form with success message
            model.addAttribute("success", true);
            return "redirect:/victim/dashboard?username=" + victim.getUsername();
        } catch (Exception e) {
            // Add error message to model if something goes wrong
            model.addAttribute("success", false);
            return "victim_form";  // Return to 'victim_form.html' on failure
        }
    }



    // Display Victim Dashboard with editable form
    @GetMapping("/dashboard")
    public String showVictimDashboard(@RequestParam String username, Model model) {
        Victim victim = victimRepository.findByUsername(username);
        model.addAttribute("victim", victim);
        return "victim_dashboard";
    }

    // Update Victim details via POST request
    @PostMapping("/updatedetails")
    public String updateVictimDetails(@ModelAttribute Victim updatedVictim, Model model) {
        Victim existingVictim = victimRepository.findByUsername(updatedVictim.getUsername());
        if (existingVictim != null) {
            existingVictim.setAddress(updatedVictim.getAddress());
            existingVictim.setCity(updatedVictim.getCity());
            victimRepository.save(existingVictim);
        }
        model.addAttribute("victim", existingVictim);
        return "redirect:/victim/dashboard?username=" + updatedVictim.getUsername();
    }
}

