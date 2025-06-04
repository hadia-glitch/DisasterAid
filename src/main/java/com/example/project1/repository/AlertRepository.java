package com.example.project1.repository;

import com.example.project1.model.AdminAlert;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlertRepository extends JpaRepository<AdminAlert, Long> {
}
