package com.example.project1.service;

import com.example.project1.DTO.TeamGroupDTO;
import com.example.project1.DTO.TeamViewDTO;
import com.example.project1.model.*;
import com.example.project1.repository.*;
import com.example.project1.wrapper.TeamForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;
@Service
public class TeamService {

    @Autowired
    private TeamRepository teamRepository;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private ResourceRepository resourceRepository;
    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<TeamViewDTO> getTeamDetailsByVolunteer(String username) {
        return teamRepository.findTeamDetailsByVolunteerUsername(username);
    }
    public List<Team> getTeamsByVolunteerUsername(String username) {
        return teamRepository.findByVolunteerUsername(username);
    }



    public List<Request> getAllRequestsByPriority() {
        return requestRepository.findAllByOrderByPriorityDesc();
    }

    public Request getRequestById(Long id) {
        return requestRepository.findById(id).orElse(null);
    }

    public List<Volunteer> getAvailableVolunteers(String skill) {
        return volunteerRepository.findBySkillAndAvailabilityTrue(skill);
    }
    public void assignVolunteerToTeam(Long requestId, String volunteerUsername) {
        List<Request> requestEntries = requestRepository.findByRequestId(requestId);
        Volunteer volunteer = volunteerRepository.findById(volunteerUsername).orElse(null);

        if (requestEntries.isEmpty() || volunteer == null) return;

        // Mark volunteer as unavailable
        volunteer.setAvailability(false);
        volunteerRepository.save(volunteer);

        for (Request req : requestEntries) {
            String resourceName = req.getResourceName();
            int requestedQuantity = req.getQuantityRequested();

            Optional<Resource> optional = resourceRepository.findByName(resourceName);
            Resource resource=null;
            if (optional.isPresent()) {
               resource = optional.get();
            }
            Team team = new Team();
            team.setTeamId(requestId);

            team.setRequestId(requestId);
            team.setVolunteerUsername(volunteerUsername);
            team.setResourceName(resourceName);
            team.setAddress(req.getAddress());
            team.setTaskDescription(req.getDescription());

            if (resource != null && resource.getQuantity() >= requestedQuantity) {
                // Enough stock: allocate
                resource.setQuantity(resource.getQuantity() - requestedQuantity);
                resourceRepository.save(resource);

                team.setQuantity(requestedQuantity);
               // team.setStatus("Assigned");
            } else {
                // Not enough stock
                team.setQuantity(0);  // or requestedQuantity to indicate attempt
               // team.setStatus("Out of Stock");
            }

            teamRepository.save(team);
        }
    }



    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public void markTeamAsComplete(Long requestId) {
        // Delete request
        requestRepository.deleteById(requestId);

        // Mark volunteers as available
        List<Team> teams = teamRepository.findByRequestId(requestId);
        for (Team team : teams) {
            Volunteer volunteer = volunteerRepository.findById(team.getVolunteerUsername()).orElse(null);
            if (volunteer != null) {
                volunteer.setAvailability(true);
                volunteerRepository.save(volunteer);
            }
        }

        // Delete team entries
        teamRepository.deleteAll(teams);
    }
    public List<TeamGroupDTO> getTeamGroupsForUser(String username) {
        List<Team> allTeams = teamRepository.findAll();

        // Find requestIds where the user is a volunteer
        Set<Long> matchingRequestIds = allTeams.stream()
                .filter(team -> username.equals(team.getVolunteerUsername()))
                .map(Team::getRequestId)
                .collect(Collectors.toSet());

        // Collect teams for those requestIds
        List<Team> relevantTeams = allTeams.stream()
                .filter(team -> matchingRequestIds.contains(team.getRequestId()))
                .collect(Collectors.toList());

        // Group by requestId and build DTOs
        return relevantTeams.stream()
                .collect(Collectors.groupingBy(Team::getRequestId))
                .entrySet()
                .stream()
                .map(entry -> {
                    Long requestId = entry.getKey();
                    List<Team> teams = entry.getValue();

                    Set<String> volunteerUsernames = teams.stream()
                            .map(Team::getVolunteerUsername)
                            .filter(Objects::nonNull)
                            .collect(Collectors.toSet());

                    // Create a map of resource and quantity, using the FIRST occurrence only
                    Map<String, Integer> resourceQuantityMap = new LinkedHashMap<>();
                    for (Team team : teams) {
                        String resource = team.getResourceName();
                        Integer quantity = team.getQuantity();
                        if (resource != null && quantity != null && !resourceQuantityMap.containsKey(resource)) {
                            resourceQuantityMap.put(resource, quantity);  // Add only first occurrence
                        }
                    }

                    Set<String> addresses = teams.stream()
                            .map(Team::getAddress)
                            .filter(Objects::nonNull)
                            .collect(Collectors.toSet());

                    Set<String> taskDescriptions = teams.stream()
                            .map(Team::getTaskDescription)
                            .filter(Objects::nonNull)
                            .collect(Collectors.toSet());

                    return new TeamGroupDTO(requestId, volunteerUsernames, resourceQuantityMap, addresses, taskDescriptions);
                })
                .collect(Collectors.toList());
    }

}



