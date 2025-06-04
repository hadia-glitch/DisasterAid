package com.example.project1.model;


import jakarta.persistence.*;
import org.hibernate.annotations.Generated;
import org.hibernate.annotations.GenerationTime;


@Entity
@Table(name = "user")
public class User {


    @Column(name = "id", insertable = false, updatable = false)
    @Generated(GenerationTime.INSERT)
    private Long id;
    @Id
    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false)

    private String password;

    private String name;

    private String email;


    // Getters and Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }


   /* public String getRole() { return role; }

    public void setRole(String role) { this.role = role; }*/
}