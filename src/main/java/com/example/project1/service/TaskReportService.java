package com.example.project1.service;

import com.example.project1.model.TaskReport;
import com.example.project1.repository.TaskReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TaskReportService {

    @Autowired
    private TaskReportRepository reportRepository;

    public void saveReport(TaskReport report) {
        reportRepository.save(report);
    }

    public List<TaskReport> getAllReports() {
        return reportRepository.findAll();
    }

    public List<TaskReport> getReportsByVolunteer(String username) {
        return reportRepository.findAllByVolunteerUsername(username);
    }
}
