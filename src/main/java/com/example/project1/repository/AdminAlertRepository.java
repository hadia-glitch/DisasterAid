package com.example.project1.repository;

import com.example.project1.model.AdminAlert;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface AdminAlertRepository extends JpaRepository<AdminAlert, Long> {
  Optional<AdminAlert> findById(Long id);
}
