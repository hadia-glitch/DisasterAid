package com.example.project1.DTO;

public class AlertDTO {
    private String title;
    private String description;
    private String severity;
    private String duration;
    private String location;
    private String mapEmbed;
    private double latitude;
    private double longitude;

    public AlertDTO(String title, String description, String severity, String duration, String location, String mapEmbed, double coord, double v) {
        this.title = title;
        this.description = description;
        this.severity = severity;
        this.duration = duration;
        this.location = location;
        this.mapEmbed = mapEmbed;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getters
    public String getTitle() { return title; }
    public String getDescription() { return description; }
    public String getSeverity() { return severity; }
    public String getDuration() { return duration; }
    public String getLocation() { return location; }
    public String getMapEmbed() { return mapEmbed; }
    public double getLatitude() { return latitude; }
    public double getLongitude() { return longitude; }
}
