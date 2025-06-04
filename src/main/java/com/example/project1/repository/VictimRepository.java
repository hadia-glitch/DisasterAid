package com.example.project1.repository;

import com.example.project1.model.Victim;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VictimRepository extends JpaRepository<Victim, String> {
    boolean existsByUsername(String username);
    void deleteByUsername(String username);
    Victim findByUsername(String username);
}
