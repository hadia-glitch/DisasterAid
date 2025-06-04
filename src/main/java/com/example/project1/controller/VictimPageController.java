/*package com.example.project1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class VictimPageController {

    // Victim Dashboard
    @GetMapping("/victim-dashboard")
    public String showVictimDashboard(@RequestParam(required = false) String username, Model model) {
        if (username == null || username.trim().isEmpty()) {
            return "redirect:/login";
        }
        model.addAttribute("username", username);
        return "victim_dashboard";
    }

    // Redirect to Update Victim Page
    @GetMapping("/victim/update-link")
    public String redirectToUpdateVictimForm(@RequestParam(required = true) String username, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("username", username);
        return "redirect:/victim/update";
    }

    // Update Victim Form Page
    @GetMapping("/victim/update")
    public String updateVictimForm(@RequestParam(required = true) String username, Model model) {
       /* if (username == null || username.trim().isEmpty()) {
            return "redirect:/login";
        }
        model.addAttribute("username", username);
        return "update_victim";
    }
}*/
