/*package com.example.project1.controller;
import java.sql.Timestamp;
import com.example.project1.model.TaskReport;
import com.example.project1.service.TaskReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

//import java.security.Timestamp;
import java.util.*;

@Controller
public class TaskReportController {

    @Autowired
    private TaskReportService reportService;

    @GetMapping("/volunteer/submitReport")
    public String showReportForm(@ModelAttribute TaskReport taskReport,
                                 @RequestParam("username") String username){
        taskReport.setVolunteerUsername(username);
        taskReport.setSubmittedAt(new Timestamp(System.currentTimeMillis()));
        return "submit_task_report";
    }

    @PostMapping("/volunteer/submitReport")
    public String submitReport(@ModelAttribute TaskReport taskReport,
                               @RequestParam("username") String username) {
        taskReport.setVolunteerUsername(username);
        taskReport.setSubmittedAt(new Timestamp(System.currentTimeMillis()));
        reportService.saveReport(taskReport);
        return "submit_task_report";
    }

    @GetMapping("/admin/viewReports")
    public String viewAllReports(Model model) {
        List<TaskReport> reports = reportService.getAllReports();
        model.addAttribute("reports", reports);
        return "view_task_reports";
    }
}*/
package com.example.project1.controller;

import com.example.project1.model.TaskReport;
import com.example.project1.service.TaskReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.List;

@Controller
public class TaskReportController {

    @Autowired
    private TaskReportService reportService;

    // Show the form to submit a report
    @GetMapping("/volunteer/submitReport")
    public String showReportForm(@RequestParam("username") String username, Model model) {
        TaskReport taskReport = new TaskReport();
        taskReport.setVolunteerUsername(username);
        model.addAttribute("taskReport", taskReport);
        model.addAttribute("username", username); // for back button
        return "submit_task_report";
    }

    // Handle form submission
    @PostMapping("/volunteer/submitReport")
    public String submitReport(@ModelAttribute TaskReport taskReport, Model model) {
        taskReport.setSubmittedAt(new Timestamp(System.currentTimeMillis()));
        reportService.saveReport(taskReport);
        model.addAttribute("message", "Report submitted successfully.");
        model.addAttribute("username", taskReport.getVolunteerUsername());
        return "submit_task_report";
    }

    // Admin view of reports
    @GetMapping("/admin/viewReports")
    public String viewAllReports(Model model) {
        List<TaskReport> reports = reportService.getAllReports();
        model.addAttribute("reports", reports);
        return "view_task_reports";
    }
}
