package com.example.project1.controller;

import com.example.project1.model.Admin;
import com.example.project1.model.Victim;
import com.example.project1.model.Volunteer;
import com.example.project1.repository.AdminRepository;
import com.example.project1.repository.VolunteerRepository;
import com.example.project1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminPageController {
    @Autowired
    private AdminRepository adminRepository;

    // Show the admin dashboard
    @GetMapping("/admin/dashboard")
    public String showAdminDashboard(@RequestParam String username, Model model) {

            Admin admin = adminRepository.findByUsername(username);
            model.addAttribute("admin", admin);

        return "admin_dashboard";  // Resolves to admin_dashboard.html
    }

    /*// Show the Add Alert page (changed mapping to /admin/add-alerts)
    @GetMapping("/admin/alert/manage")
    public String showAddAlertPage(@RequestParam("username") String username, Model model) {

        Admin admin = adminRepository.findByUsername(username);
        model.addAttribute("admin", admin);
        return "alert_management";
    }
*/
    // Show the Add Resource page
    /*@GetMapping("/admin/resources")
    public String showAddResourcePage(@RequestParam("username") String username, Model model) {

        Admin admin = adminRepository.findByUsername(username);
        model.addAttribute("admin", admin);
        return "resources";  // Resolves to add_resource.html (page where resources are added)
    }*/
}
