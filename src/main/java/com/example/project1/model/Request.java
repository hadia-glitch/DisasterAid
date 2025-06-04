package com.example.project1.model;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "request")
public class Request {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "request_id", nullable = false)
    private Long requestId;

    @Column(name = "victim_username", nullable = false)
    private String victimUsername;

    @Column(nullable = false)
    private String priority;

    @Column(name = "address",nullable = false)
    private String Address;

    @Column(name = "people_needing_aid", nullable = false)
    private int peopleNeedingAid;

    @Column(name = "volunteer_skills", nullable = false)
    private String volunteerSkills;

    @Column(nullable = false)
    private String description;

    @Column(name = "resource_name", nullable = false)
    private String resourceName;

    @Column(name = "quantity_requested", nullable = false)
    private int quantityRequested;

    @Column(nullable = false)
    private String status = "pending";


    // Getters and setters

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getRequestId() { return requestId; }
    public void setRequestId(Long requestId) { this.requestId = requestId; }

    public String getVictimUsername() { return victimUsername; }
    public void setVictimUsername(String victimUsername) { this.victimUsername = victimUsername; }

    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public int getPeopleNeedingAid() { return peopleNeedingAid; }
    public void setPeopleNeedingAid(int peopleNeedingAid) { this.peopleNeedingAid = peopleNeedingAid; }

    public String getVolunteerSkills() { return volunteerSkills; }
    public void setVolunteerSkills(String volunteerSkills) { this.volunteerSkills = volunteerSkills; }

    public String getAddress() { return Address; }
    public void setAddress(String Address) { this.Address = Address; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getResourceName() { return resourceName; }
    public void setResourceName(String resourceName) { this.resourceName = resourceName; }

    public int getQuantityRequested() { return quantityRequested; }
    public void setQuantityRequested(int quantityRequested) { this.quantityRequested = quantityRequested; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
