package com.example.project1.controller;

import com.example.project1.DTO.TeamGroupDTO;
import com.example.project1.DTO.TeamViewDTO;
import com.example.project1.model.Team;
import com.example.project1.model.User;
import com.example.project1.model.Volunteer;
import com.example.project1.repository.TeamRepository;
import com.example.project1.service.TeamService;
import com.example.project1.service.VolunteerService;
import com.example.project1.repository.VolunteerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/volunteer")
public class VolunteerController {

    @Autowired
    private VolunteerRepository volunteerRepository;

    @Autowired
    private VolunteerService volunteerService; // Properly autowired now
    @Autowired
    private TeamService teamService;

    @Autowired
    private VolunteerRepository userService;

    // Show volunteer login/registration form
    @GetMapping("/login")
    public String showLoginForm(Model model) {
        model.addAttribute("volunteer", new Volunteer());
        return "volunteer_login";
    }

    // Handle volunteer login/registration
    @PostMapping("/login")
    public String loginOrRegister(@ModelAttribute Volunteer volunteer) {
        Volunteer existing = volunteerRepository.findByUsername(volunteer.getUsername());

        if (existing != null) {
            // Volunteer already exists, treat as login
            return "redirect:/volunteer/dashboard?username=" + existing.getUsername();
        } else {
            // New volunteer, register
            volunteerRepository.save(volunteer);
            return "redirect:/volunteer/dashboard?username=" + volunteer.getUsername();
        }
    }

    // Show volunteer dashboard (merged with update form)
    @GetMapping("/dashboard")
    public String showDashboard(@RequestParam String username, Model model) {
        Volunteer volunteer = volunteerRepository.findByUsername(username);
        if (volunteer == null) {
            return "redirect:/volunteer/login";
        }
        model.addAttribute("volunteer", volunteer);
        return "volunteer_dashboard"; // Dashboard contains editable fields
    }

    // Handle update from dashboard
    @PostMapping("/dashboard")
    public String updateVolunteerFromDashboard(@ModelAttribute Volunteer volunteer) {
        volunteerService.updateVolunteer(volunteer);
        return "redirect:/volunteer/dashboard?username=" + volunteer.getUsername();
    }

   /* @GetMapping("/view_assignments")
    public String viewVolunteerAllocation(@RequestParam("username") String username, Model model) {
        Volunteer volunteer = volunteerRepository.findByUsername(username);
        List<TeamViewDTO> filteredTeams = teamService.getTeamDetailsByVolunteer(username);
        model.addAttribute("teams", filteredTeams);
        return "view_assignments";
    }*/
  /* @GetMapping("/view_assignments")
   public String viewVolunteerAssignments(@RequestParam("username") String username, Model model) {
       List<Team> assignedTeams = teamService.getTeamsByVolunteerUsername(username);

       // Optional: group by requestId if needed
       Map<Long, List<Team>> teamsGrouped = new LinkedHashMap<>();
       for (Team t : assignedTeams) {
           teamsGrouped.computeIfAbsent(t.getRequestId(), k -> new ArrayList<>()).add(t);
       }

       model.addAttribute("teamsGrouped", teamsGrouped);
       return "view_assignments";
   }*/@Autowired
   TeamRepository teamRepository;
   @GetMapping("/view_assignments")
   public String viewAssignments(@RequestParam("username") String username, Model model) {
       // Fetch the team groups for the user
       List<TeamGroupDTO> teamGroups = teamService.getTeamGroupsForUser(username);


       // Add the teamGroups data to the model for the view to use
       model.addAttribute("teamGroups", teamGroups);

       // Return the view name (the HTML page)
       return "view_assignments";
   }

}









 /*  // Redirect to volunteer update form
   @GetMapping("/volunteer/update")
    public String updateVolunteer(@RequestParam("username") String username, Model model) {
        // Redirect to the update form URL
        return "redirect:/volunteer/update-victim?username=" + username;
    }*/


