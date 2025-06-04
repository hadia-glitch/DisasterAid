package com.example.project1.repository;

import com.example.project1.model.TaskReport;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskReportRepository extends JpaRepository<TaskReport, Long> {
    List<TaskReport> findAllByVolunteerUsername(String username);
}
