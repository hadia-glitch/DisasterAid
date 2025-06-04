/*package com.example.project1.controller;

import com.example.project1.model.*;
import com.example.project1.repository.RequestRepository;
import com.example.project1.repository.VolunteerRepository;
import com.example.project1.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/admin/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;

    @GetMapping("/create-team")
    public String showCreateTeamPage(Model model) {
        List<Long> requestIds = requestRepository.findDistinctRequestIds();
        model.addAttribute("requestIds", requestIds);
        return "create_team";
    }
    @PostMapping("/create-team")
    public String showVolunteersForRequest(@RequestParam("requestId") Long requestId, Model model) {
        // Get the request based on the selected requestId
        List<Request> requests = requestRepository.findByRequestId(requestId);
        if (requests.isEmpty()) {
            throw new IllegalArgumentException("Invalid request ID");
        }


        // Get the required skill for the request
        String requiredSkill = requests.get(0).getVolunteerSkills();

        // Fetch volunteers whose skills match the required skill for the request
        List<Volunteer> volunteers = volunteerRepository.findBySkillAndAvailabilityTrue(requiredSkill);

        // Add the volunteers list and requestId to the model for rendering the form
        model.addAttribute("volunteers", volunteers);
        model.addAttribute("requestId", requestId);

        return "create_team";  // Returning back to 'create-team.html' to show the volunteers
    }




    @PostMapping("/assign")
    public String assignVolunteers(@RequestParam Long requestId,
                                   @RequestParam(name = "volunteerUsernames") List<String> volunteerUsernames) {
        for (String username : volunteerUsernames) {
            teamService.assignVolunteerToTeam(requestId, username);
        }
        return "redirect:/admin/teams/view";
    }

    @GetMapping("/view")
    public String viewTeams(Model model) {
        List<Team> teams = teamService.getAllTeams();
        Map<Long, List<Team>> teamsGrouped = teams.stream()
                .collect(Collectors.groupingBy(Team::getRequestId));
        model.addAttribute("teamsGrouped", teamsGrouped.entrySet());
        return "view_teams";
    }

    @PostMapping("/complete")
    public String completeTeam(@RequestParam Long requestId) {
        teamService.markTeamAsComplete(requestId);
        return "redirect:/admin/teams/view";
    }

    @GetMapping("/request-details")
    @ResponseBody
    public Request getRequestDetails(@RequestParam("requestId") Long requestId) {
        return requestRepository.findByRequestId(requestId).stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));
    }
}*/
package com.example.project1.controller;

import com.example.project1.model.Admin;
import com.example.project1.model.Request;
import com.example.project1.model.Team;
import com.example.project1.model.Volunteer;
import com.example.project1.repository.AdminRepository;
import com.example.project1.repository.RequestRepository;
import com.example.project1.repository.VolunteerRepository;
import com.example.project1.service.TeamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
@RequestMapping("/admin/teams")
public class TeamController {

    @Autowired
    private TeamService teamService;

    @Autowired
    private RequestRepository requestRepository;

    @Autowired
    private VolunteerRepository volunteerRepository;
    @Autowired
    private AdminRepository adminRepository;

    @GetMapping("/create-team")
    public String showCreateTeamPage(@RequestParam("username") String username,Model model) {

        Admin admin = adminRepository.findByUsername(username);
        model.addAttribute("admin", admin);
        List<Long> requestIds = requestRepository.findDistinctRequestIds();
        model.addAttribute("requestIds", requestIds);
        return "create_team";
    }

    @PostMapping("/create-team")
    public String showVolunteersForRequest(@RequestParam("username") String username,@RequestParam("requestId") Long requestId, Model model) {
        Admin admin = adminRepository.findByUsername(username);
        model.addAttribute("admin", admin);
        List<Request> requests = requestRepository.findByRequestId(requestId);
        if (requests.isEmpty()) {
            throw new IllegalArgumentException("Invalid request ID");
        }

        String requiredSkill = requests.get(0).getVolunteerSkills();
        List<Volunteer> volunteers = volunteerRepository.findBySkillAndAvailabilityTrue(requiredSkill);

        model.addAttribute("volunteers", volunteers);
        model.addAttribute("requestId", requestId);
        return "create_team";
    }

    @PostMapping("/assign")
    public String assignVolunteers(@RequestParam("username") String username,Model model,@RequestParam Long requestId,
                                   @RequestParam(name = "volunteerUsernames") List<String> volunteerUsernames) {
       Admin admin = adminRepository.findByUsername(username);
        model.addAttribute("admin", admin);
        for (String vusername : volunteerUsernames) {
            teamService.assignVolunteerToTeam(requestId, vusername);
        }
        return "redirect:/admin/teams/view";
    }

    /*@GetMapping("/view")
    public String viewTeams(@RequestParam("username") String username ,@RequestParam("username") String username,Model model) {
        /*Admin admin = adminRepository.findByUsername(username);
        model.addAttribute("admin", admin);
        List<Team> teams = teamService.getAllTeams();

        Map<Long, Map<String, Object>> groupedData = new HashMap<>();

        for (Team t : teams) {
            Long requestId = t.getRequestId();
            Map<String, Object> data = groupedData.computeIfAbsent(requestId, k -> new HashMap<>());

            data.putIfAbsent("address", t.getAddress());
            data.putIfAbsent("taskDescription", t.getTaskDescription());

            Set<String> volunteerUsernames = (Set<String>) data.computeIfAbsent("volunteers", k -> new HashSet<>());
            volunteerUsernames.add(t.getVolunteerUsername());

            Map<String, Integer> resources = (Map<String, Integer>) data.computeIfAbsent("resources", k -> new LinkedHashMap<>());
            resources.put(t.getResourceName(), t.getQuantity()); // last one wins if duplicate
        }

        model.addAttribute("teamsGrouped", groupedData);
        return "redirect:/admin/teams/view";
    }

    @PostMapping("/complete")
    public String completeTeam(@RequestParam("username") String username,Model model,@RequestParam Long requestId) {
        Admin admin = adminRepository.findByUsername(username);
        model.addAttribute("admin", admin);
        teamService.markTeamAsComplete(requestId);
        return "redirect:/admin/teams/view";
    }

    @GetMapping("/request-details")
    @ResponseBody
    public Request getRequestDetails(@RequestParam("requestId") Long requestId) {
        return requestRepository.findByRequestId(requestId).stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));
    }*/
    @GetMapping("/view")
    public String viewTeams(Model model) {
        List<Team> teams = teamService.getAllTeams();

        Map<Long, Map<String, Object>> groupedData = new HashMap<>();

        for (Team t : teams) {
            Long requestId = t.getRequestId();
            Map<String, Object> data = groupedData.computeIfAbsent(requestId, k -> new HashMap<>());

            data.putIfAbsent("address", t.getAddress());
            data.putIfAbsent("taskDescription", t.getTaskDescription());

            Set<String> volunteerUsernames = (Set<String>) data.computeIfAbsent("volunteers", k -> new HashSet<>());
            volunteerUsernames.add(t.getVolunteerUsername());

            Map<String, Integer> resources = (Map<String, Integer>) data.computeIfAbsent("resources", k -> new LinkedHashMap<>());
            resources.put(t.getResourceName(), t.getQuantity()); // last one wins if duplicate
        }

        model.addAttribute("teamsGrouped", groupedData);
        return "view_teams";
    }

    @PostMapping("/complete")
    public String completeTeam(@RequestParam Long requestId) {
        teamService.markTeamAsComplete(requestId);
        return "redirect:/admin/teams/view";
    }

    @GetMapping("/request-details")
    @ResponseBody
    public Request getRequestDetails(@RequestParam("requestId") Long requestId) {
        return requestRepository.findByRequestId(requestId).stream()
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Request not found"));
    }

}
