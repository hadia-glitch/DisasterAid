package com.example.project1.service;


import com.example.project1.model.AdminAlert;
import com.example.project1.repository.AdminAlertRepository;
import com.example.project1.service.AdminAlertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class AdminAlertServiceImpl implements AdminAlertService {

    private final AdminAlertRepository alertRepository;

    public AdminAlertServiceImpl(AdminAlertRepository alertRepository) {
        this.alertRepository = alertRepository;
    }

    @Override
    public void saveAlert(AdminAlert alert) {
        alertRepository.save(alert);
    }

    @Override
    public void deleteAlertById(Long id) {
        alertRepository.deleteById(id);
    }

    @Override
    public AdminAlert getAlertById(Long id) {
        return alertRepository.findById(id).orElse(null);
    }

    @Override
    public void updateAlert(AdminAlert updatedAlert) {
        alertRepository.save(updatedAlert);
    }

    @Override
    public List<AdminAlert> getAllAlerts() {
        return alertRepository.findAll();
    }
}
