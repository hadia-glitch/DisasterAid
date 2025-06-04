package com.example.project1.service;



import com.example.project1.model.Admin;
import com.example.project1.model.User;
import com.example.project1.model.Victim;
import com.example.project1.model.Volunteer;
import com.example.project1.repository.UserRepository;
import com.example.project1.repository.VictimRepository;
import com.example.project1.repository.VolunteerRepository;
import com.example.project1.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User saveUser(User user) {
        return userRepository.save(user);
    }

   /* public boolean userExists(String username) {
        return userRepository.existsByUsername(username);
    }*/

    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private VictimRepository victimRepository;

    public boolean userExists(String username) {
        return volunteerRepository.existsByUsername(username) ||
                adminRepository.existsByUsername(username) ||
                victimRepository.existsByUsername(username);
    }

    public boolean isVolunteer(String username) {
        return volunteerRepository.existsByUsername(username);
    }

    public boolean isAdmin(String username) {
        return adminRepository.existsByUsername(username);
    }

    public boolean isVictim(String username) {
        return victimRepository.existsByUsername(username);
    }
    public void updateVolunteerDetails(Volunteer volunteer) {
        volunteerRepository.save(volunteer); // assumes the username already exists
    }

    public void updateVictimDetails(Victim victim) {
        victimRepository.save(victim); // same assumption
    }
    public String getAdminKeyIfExists(String username) {
        Admin admin = adminRepository.findByUsername(username);
        return (admin != null) ? admin.getAdminKey() : null;
    }

}
