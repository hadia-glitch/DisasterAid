package com.example.project1.wrapper;

import com.example.project1.model.Team;

import java.util.LinkedHashSet;
import java.util.Set;

public class TeamForm {

    private String name;
    private String taskDescription;

    private Long requestId;
    private Team team;
    private Set<String> volunteerUsernames = new LinkedHashSet<>();
    public TeamForm(Team team) {
        this.team = team;
        // if your Team entity only has a single volunteerUsername field,
        // you may need to collect all volunteers elsewhere—
        // otherwise leave this empty and fill volunteerUsernames in your service
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public Set<String> getVolunteerUsernames() {
        return volunteerUsernames;
    }

    public void setVolunteerUsernames(Set<String> volunteerUsernames) {
        this.volunteerUsernames = volunteerUsernames;
    }

    public Long getRequestId() {
        return requestId;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }
}
