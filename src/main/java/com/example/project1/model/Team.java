package com.example.project1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "teams")
public class Team {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;  // Unique row identifier

    private Long teamId; // Equals requestId (used to group rows by team)

    private Long requestId;

    private String volunteerUsername;

    private String address;

    private String taskDescription;

    private String resourceName;

    private int quantity;

    public Team() {}

    public Team(Long requestId, String address, String taskDescription, String volunteerUsername, String resourceName, int quantity) {
        this.requestId = requestId;
        this.teamId = requestId;  // important line
        this.address = address;
        this.taskDescription = taskDescription;
        this.volunteerUsername = volunteerUsername;
        this.resourceName = resourceName;
        this.quantity = quantity;
    }

    // Getters and setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getTeamId() { return teamId; }
    public void setTeamId(Long teamId) { this.teamId = teamId; }

    public Long getRequestId() { return requestId; }
    public void setRequestId(Long requestId) {
        this.requestId = requestId;
        this.teamId = requestId;  // ensure it's always equal
    }

    public String getVolunteerUsername() { return volunteerUsername; }
    public void setVolunteerUsername(String volunteerUsername) { this.volunteerUsername = volunteerUsername; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }

    public String getTaskDescription() { return taskDescription; }
    public void setTaskDescription(String taskDescription) { this.taskDescription = taskDescription; }

    public String getResourceName() { return resourceName; }
    public void setResourceName(String resourceName) { this.resourceName = resourceName; }

    public int getQuantity() { return quantity; }
    public void setQuantity(int quantity) { this.quantity = quantity; }
}
