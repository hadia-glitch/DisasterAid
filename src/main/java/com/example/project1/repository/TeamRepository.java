package com.example.project1.repository;

import com.example.project1.DTO.TeamViewDTO;
import com.example.project1.model.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {

    List<Team> findByRequestId(Long requestId);
    @Query("""
    SELECT new com.example.project1.DTO.TeamViewDTO(
        t.teamId, t.requestId, t.address, t.taskDescription,
        t.volunteerUsername, t.resourceName, t.quantity)
    FROM Team t
    WHERE t.volunteerUsername = :username
""")
    List<TeamViewDTO> findTeamDetailsByVolunteerUsername(@Param("username") String username);
    List<Team> findByVolunteerUsername(String volunteerUsername);


}
