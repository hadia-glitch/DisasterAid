package com.example.project1.controller;

import com.example.project1.model.Admin;
import com.example.project1.service.AdminSignInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdminSignInController {

    @Autowired
    private AdminSignInService adminSignInService;

    @GetMapping("/admin-auth")
    public String showAdminAuthForm(Model model) {
        model.addAttribute("admin", new Admin());
        return "admin_auth";
    }

    @PostMapping("/admin-auth")
    public String verifyAdmin(@ModelAttribute("admin") Admin admin, Model model) {
        Admin existingAdmin = adminSignInService.findByUsername(admin.getUsername());

        if (existingAdmin != null && existingAdmin.getAdminKey().equals(admin.getAdminKey())) {
            return "redirect:/admin-dashboard";
        } else {
            model.addAttribute("error", "Invalid username or admin key");
            return "admin_auth";
        }
    }

    @GetMapping("/admin-dashboard")
    public String showAdminDashboard() {
        return "admin_dashboard";
    }
}
