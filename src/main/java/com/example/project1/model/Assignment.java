package com.example.project1.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "assignment")
public class Assignment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int quantityAssigned;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

    @ManyToOne
    @JoinColumn(name = "assigned_to_team")
    private Team assignedToTeam;

    private String assignedByAdmin;

    private LocalDateTime assignedAt;

    // Getters and Setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getQuantityAssigned() {
        return quantityAssigned;
    }

    public void setQuantityAssigned(int quantityAssigned) {
        this.quantityAssigned = quantityAssigned;
    }

    public Resource getResource() {
        return resource;
    }

    public void setResource(Resource resource) {
        this.resource = resource;
    }

    public Team getAssignedToTeam() {
        return assignedToTeam;
    }

    public void setAssignedToTeam(Team assignedToTeam) {
        this.assignedToTeam = assignedToTeam;
    }

    public String getAssignedByAdmin() {
        return assignedByAdmin;
    }

    public void setAssignedByAdmin(String assignedByAdmin) {
        this.assignedByAdmin = assignedByAdmin;
    }

    public LocalDateTime getAssignedAt() {
        return assignedAt;
    }

    public void setAssignedAt(LocalDateTime assignedAt) {
        this.assignedAt = assignedAt;
    }
}
