package com.example.project1.service;

import com.example.project1.model.Volunteer;
import com.example.project1.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VolunteerService {

    @Autowired
    private VolunteerRepository volunteerRepository;

    public Volunteer findByUsername(String username) {
        return volunteerRepository.findByUsername(username);
    }
    public Volunteer getVolunteerByUsername(String username) {
        return volunteerRepository.findByUsername(username);
    }

    public void saveOrUpdateVolunteer(Volunteer volunteer) {
        volunteerRepository.save(volunteer);
    }
    public void updateVolunteer(Volunteer updated) {
        Volunteer existing = volunteerRepository.findByUsername(updated.getUsername());
        if (existing != null) {
            existing.setSkill(updated.getSkill());
            existing.setAvailability(updated.getAvailability());
            volunteerRepository.save(existing);
        }
}}
