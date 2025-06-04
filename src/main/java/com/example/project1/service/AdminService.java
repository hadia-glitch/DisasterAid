/*package com.example.project1.service;

import com.example.project1.model.DisasterAlert;
import com.example.project1.model.Resource;
import com.example.project1.repository.DisasterAlertRepository;
import com.example.project1.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private DisasterAlertRepository disasterAlertRepository;

    @Autowired
    private ResourceRepository resourceRepository;

    // Method to add a new disaster alert
    public DisasterAlert addDisasterAlert(DisasterAlert disasterAlert) {
        return disasterAlertRepository.save(disasterAlert);
    }

    // Method to update the resource quantity
    public Resource updateResourceQuantity(String type, int quantityToAdd) {
        Resource resource = resourceRepository.findByName(type);
        if (resource != null) {
            resource.setQuantity(resource.getQuantity() + quantityToAdd);
            return resourceRepository.save(resource);
        }
        return null;
    }

    public List<DisasterAlert> getAllAlerts() {
        return disasterAlertRepository.findAll();
    }
}
*/