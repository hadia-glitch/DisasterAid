package com.example.project1.service;

import com.example.project1.model.Victim;
import com.example.project1.repository.VictimRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VictimService {

    @Autowired
    private VictimRepository victimRepository;

    // Fetch victim by username
    public Victim getVictimByUsername(String username) {
        Optional<Victim> victimOpt = victimRepository.findById(username);
        return victimOpt.orElse(null);  // Return null if victim not found
    }

    // Save or update the victim
    /*public void saveVictim(Victim victim) {
        // If victim exists, update their details
        Optional<Victim> existingVictimOpt = victimRepository.findById(victim.getUsername());
        if (existingVictimOpt.isPresent()) {
            Victim existingVictim = existingVictimOpt.get();
            existingVictim.setAddress(victim.getAddress());  // Update address
            existingVictim.setCity(victim.getCity());  // Update city
            victimRepository.save(existingVictim);  // Save the updated victim
        } else {
            throw new RuntimeException("Victim not found with username: " + victim.getUsername());
        }*/
        public void saveVictim(Victim victim) {
            victimRepository.save(victim);

    }
}
