package com.example.project1.controller;

import com.example.project1.model.Admin;
import com.example.project1.model.AdminAlert;
import com.example.project1.repository.AdminRepository;
import com.example.project1.service.AdminAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
@Controller
@RequestMapping("/admin/alert")
public class AdminAlertController {
    @Autowired
    private AdminRepository adminRepository;
    private final AdminAlertService alertService;

    public AdminAlertController(AdminAlertService alertService) {
        this.alertService = alertService;
    }

    @GetMapping("/manage")
    public String showManagePage(@RequestParam("username") String username,Model model) {
        Admin admin = adminRepository.findByUsername(username);
        model.addAttribute("admin", admin);
        model.addAttribute("adminAlert", new AdminAlert());
        model.addAttribute("editAlert", new AdminAlert());
        model.addAttribute("alerts", alertService.getAllAlerts());
        return "alert_management";
    }

    @PostMapping("/create")
    public String createAlert(@RequestParam("username") String username,Model model,@ModelAttribute AdminAlert adminAlert) {
        Admin admin = adminRepository.findByUsername(username);
        model.addAttribute("admin", admin);
        if (adminAlert.getLocation() != null && !adminAlert.getLocation().isBlank()) {
            String embed = "<iframe width='100%' height='200' style='border:0' loading='lazy' allowfullscreen " +
                    "referrerpolicy='no-referrer-when-downgrade' src='https://www.google.com/maps?q=" +
                    adminAlert.getLocation().replace(" ", "+") + "&output=embed'></iframe>";
            adminAlert.setMapEmbed(embed);
        }
        alertService.saveAlert(adminAlert);
        return "redirect:/admin/alert/disaster_alerts?username"+username;
    }

    @PostMapping("/delete")
    public String deleteAlert(@RequestParam("username") String username,Model model,@RequestParam Long id) {
        Admin admin = adminRepository.findByUsername(username);
        model.addAttribute("admin", admin);
        alertService.deleteAlertById(id);
        return "redirect:/admin/alert/disaster_alerts?username"+username;
    }

    @PostMapping("/update")
    public String updateAlert(@RequestParam("username") String username,Model model,@ModelAttribute AdminAlert alert) {
        Admin admin = adminRepository.findByUsername(username);
        model.addAttribute("admin", admin);
        if (alert.getLocation() != null && !alert.getLocation().isBlank()) {
            String embed = "<iframe width='100%' height='200' style='border:0' loading='lazy' allowfullscreen " +
                    "referrerpolicy='no-referrer-when-downgrade' src='https://www.google.com/maps?q=" +
                    alert.getLocation().replace(" ", "+") + "&output=embed'></iframe>";
            alert.setMapEmbed(embed);
        }
        alertService.updateAlert(alert);
        return "redirect:/admin/alert/disaster_alerts?username"+username;
    }

    @GetMapping("/disaster_alerts")
    public String showAllAlerts(Model model) {
        model.addAttribute("alerts", alertService.getAllAlerts());
        return "disaster_alerts";
    }
}
