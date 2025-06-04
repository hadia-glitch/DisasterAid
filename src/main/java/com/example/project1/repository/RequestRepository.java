
package com.example.project1.repository;

import com.example.project1.model.Request;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface RequestRepository extends JpaRepository<Request, Long> {

    @Query("SELECT COALESCE(MAX(r.requestId), 0) + 1 FROM Request r")
    Long getNextRequestId();
    List<Request> findAllByOrderByPriorityDesc();

    List<Request> findByRequestId(Long requestId);

    // Find all requests with given status
    List<Request> findByStatus(String status);
  //  List<Request> findAllByRequestId();
  @Query("SELECT r.requestId FROM Request r")
  List<Long> findAllRequestIds();
    @Query("SELECT DISTINCT r.requestId FROM Request r")
    List<Long> findDistinctRequestIds();


    // Find all requests with a given priority (e.g. "high", "medium", "low")
    List<Request> findByPriority(String priority);

    // Find by victimUsername
    List<Request> findByVictimUsername(String victimUsername);

    // Find requests by volunteerSkills
    List<Request> findByVolunteerSkillsContaining(String skill); // for partial match

    // Find by resource name
    List<Request> findByResourceName(String resourceName);
}
