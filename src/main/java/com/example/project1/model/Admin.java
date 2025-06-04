package com.example.project1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "admin")
public class Admin {

    @Id
    private String username; // Foreign key to User.username

    @Column(name = "admin_key")
    private String adminKey;

    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username", insertable = false, updatable = false)
    private User user;

    // Getters and Setters
    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getAdminKey() { return adminKey; }

    public void setAdminKey(String adminKey) { this.adminKey = adminKey; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
}