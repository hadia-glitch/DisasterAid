/*
package com.example.project1.controller;



import com.example.project1.model.DisasterAlert;
import com.example.project1.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @Autowired
    private AdminService adminService;

    // GET request for showing disaster alert form
    @GetMapping("/admin/alerts")
    public String showAlertForm() {
        return "alertForm"; // Name of the HTML file for alert form
    }

    // POST request to save the new disaster alert
    @PostMapping("/admin/submit-alert")
    public String submitAlert(@RequestParam("alertName") String alertName,
                              @RequestParam("alertType") String alertType,
                              @RequestParam("location") String location,
                              @RequestParam("description") String description,
                              @RequestParam("severity") String severity) {
        DisasterAlert disasterAlert = new DisasterAlert(alertName, alertType, location, description, severity);
        adminService.addDisasterAlert(disasterAlert);
        return "redirect:/admin/alerts"; // Redirect after submission
    }

    // Display all disaster alerts in the "Disaster Alerts" tab
    @GetMapping("/admin/alerts-list")
    public String showAlertsList(Model model) {
        model.addAttribute("alerts", adminService.getAllAlerts());
        return "alerts"; // Display alerts.html
    }
}
*/