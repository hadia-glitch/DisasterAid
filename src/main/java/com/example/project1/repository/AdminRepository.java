package com.example.project1.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import com.example.project1.model.Admin;
import org.springframework.stereotype.Repository;


public interface AdminRepository extends JpaRepository<Admin, String> {
    Admin findByUsername(String username);
    void deleteByUsername(String username);

    boolean existsByUsername(String username);
}
