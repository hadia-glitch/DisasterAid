/*package com.example.project1.controller;

import com.example.project1.model.Volunteer;
import com.example.project1.service.VolunteerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VolunteerPageController {

   /* @Autowired
    private VolunteerService volunteerService;

    // Volunteer Dashboard
    @GetMapping("/volunteer-dashboard")
    public String showVolunteerDashboard(@RequestParam(required = false) String username, Model model) {
        if (username == null || username.trim().isEmpty()) {
            return "redirect:/login";
        }
        model.addAttribute("username", username);
        return "volunteer_dashboard";
    }

    // Redirect to Update Volunteer Page
    @GetMapping("/volunteer/update-link")
    public String redirectToUpdateVolunteerForm(@RequestParam(required = false) String username, Model model) {
        model.addAttribute("username", username);
        return "redirect:/update-volunteer";
    }

    // Update Volunteer Form Page
    @GetMapping("/update-volunteer")
    public String updateVolunteerForm(@RequestParam(required = true) String username, Model model) {
        /*if (username == null || username.trim().isEmpty()) {
            return "redirect:/login";
        }
        Volunteer volunteer = volunteerService.findByUsername(username);
        if (volunteer == null) {
            return "redirect:/volunteer/login";
        }
        model.addAttribute("volunteer", volunteer);
        return "update_volunteer";
    }*/


