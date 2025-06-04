package com.example.project1.service;

import com.example.project1.model.AdminAlert;

import java.util.List;
import java.util.Optional;

public interface AdminAlertService {
    void saveAlert(AdminAlert alert);
    void deleteAlertById(Long id);
    AdminAlert getAlertById(Long id);
    void updateAlert(AdminAlert alert);
    List<AdminAlert> getAllAlerts();
}
