package com.example.project1.model;

import jakarta.persistence.*;

@Entity
@Table(name = "victim")
public class Victim {

    @Id
    private String username;

    private String contact;

    private String address;

    private String city;

    // Getters and Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String  contact) {
        this. contact =  contact;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

}
