package com.example.project1.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "disaster_alert")
public class AdminAlert {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String description;
    private String severity;
    private String location;

    @Column(length = 2000)
    private String mapEmbed;

    private String duration; // ⏱️ new field

    private LocalDateTime issuedAt;

    @PrePersist
    public void onCreate() {
        if (issuedAt == null) {
            issuedAt = LocalDateTime.now();
        }
    }

    // Getters and Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getSeverity() { return severity; }
    public void setSeverity(String severity) { this.severity = severity; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getMapEmbed() { return mapEmbed; }
    public void setMapEmbed(String mapEmbed) { this.mapEmbed = mapEmbed; }

    public String getDuration() { return duration; }
    public void setDuration(String duration) { this.duration = duration; }

    public LocalDateTime getIssuedAt() { return issuedAt; }
    public void setIssuedAt(LocalDateTime issuedAt) { this.issuedAt = issuedAt; }
}
