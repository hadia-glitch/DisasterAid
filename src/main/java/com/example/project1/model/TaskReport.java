package com.example.project1.model;

import jakarta.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "task_report")
public class TaskReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String teamId;

    private String requestId;

    private String volunteerUsername;

    @Column(columnDefinition = "TEXT")
    private String description;

    private String timeDuration;

    private String successRate;

    @Column(columnDefinition = "TEXT")
    private String resourcesUsed;

    private Timestamp submittedAt;

    // --- Getters and Setters ---

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getVolunteerUsername() {
        return volunteerUsername;
    }

    public void setVolunteerUsername(String volunteerUsername) {
        this.volunteerUsername = volunteerUsername;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTimeDuration() {
        return timeDuration;
    }

    public void setTimeDuration(String timeDuration) {
        this.timeDuration = timeDuration;
    }

    public String getSuccessRate() {
        return successRate;
    }

    public void setSuccessRate(String successRate) {
        this.successRate = successRate;
    }

    public String getResourcesUsed() {
        return resourcesUsed;
    }

    public void setResourcesUsed(String resourcesUsed) {
        this.resourcesUsed = resourcesUsed;
    }

    public Timestamp getSubmittedAt() {
        return submittedAt;
    }

    public void setSubmittedAt(Timestamp submittedAt) {
        this.submittedAt = submittedAt;
    }
}
