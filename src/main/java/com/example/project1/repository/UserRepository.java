package com.example.project1.repository;



import com.example.project1.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);
    boolean existsByUsername(String username);
    void deleteByUsername(String username);

}

