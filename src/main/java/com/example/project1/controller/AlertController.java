package com.example.project1.controller;

import com.example.project1.DTO.AlertDTO;
import com.example.project1.service.AlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alerts")
@CrossOrigin(origins = "*") // Needed if serving HTML from different port
public class AlertController {

    @Autowired
    private AlertService alertService;

    @GetMapping
    public List<AlertDTO> getAlerts() {
        return alertService.getAllAlerts();
    }

}
