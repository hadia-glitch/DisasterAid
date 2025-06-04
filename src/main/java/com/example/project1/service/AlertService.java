package com.example.project1.service;



import com.example.project1.DTO.AlertDTO;
import com.example.project1.model.AdminAlert;
import com.example.project1.repository.AlertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;
import com.example.project1.controller.GeocodingService;
@Service
public class AlertService {

    @Autowired
    private AlertRepository alertRepository;

    public List<AlertDTO> getAllAlerts() {
        List<AdminAlert> alerts = alertRepository.findAll();
        return alerts.stream().map(alert -> {
            double[] coords = GeocodingService.getLatLonFromLocation(alert.getLocation());
            return new AlertDTO(
                    alert.getTitle(),
                    alert.getDescription(),
                    alert.getSeverity(),
                    alert.getDuration(),
                    alert.getLocation(),
                    alert.getMapEmbed(),
                    coords[0],  // latitude
                    coords[1]   // longitude
            );
        }).collect(Collectors.toList());
    }
}
