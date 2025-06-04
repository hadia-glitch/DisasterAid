package com.example.project1.repository;

import com.example.project1.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.project1.model.Resource;

import java.util.List;
import java.util.Optional;

@Repository
public interface ResourceRepository extends JpaRepository<Resource, Long> {

    // findAll() is already provided by JpaRepository, so no need to redeclare it
    // ✅ ResourceRepository.java

       // List<Resource> findByRequestId(Long requestId); // return Resources, not Request



    Optional<Resource> findById(Long id); // Use Optional to match JpaRepository contract
 //Request findByRequestId(Long requestId);
    Optional<Resource> findByName(String name);  // Custom query to find by name
   // Resource findByName(String name);
    // Resource findByNaame(String Name);
}
