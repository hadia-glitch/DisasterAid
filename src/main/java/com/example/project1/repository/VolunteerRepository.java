package com.example.project1.repository;

import com.example.project1.model.Volunteer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface VolunteerRepository extends JpaRepository<Volunteer, String> {
    boolean existsByUsername(String username);
    Volunteer findByUsername(String username);
    void deleteByUsername(String username);
    List<Volunteer> findBySkillAndAvailability(String skill, boolean availability);
    List<Volunteer> findBySkillAndAvailabilityTrue(String skill);
    List<Volunteer> findBySkill(String skill);

}
