package com.example.project1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "volunteer")
public class Volunteer {

    @Id
    private String username;

    @Column(nullable = false)
    private String skill;

    @Column(nullable = false)
    private Boolean availability;

    @Column(nullable = false)
    private String location;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Boolean getAvailability() {
        return availability;
    }

    public void setAvailability(Boolean availability) {
        this.availability = availability;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
