package com.example.project1.wrapper;

import java.util.List;

public class RequestWrapper {
    private String victimUsername;
    private String priority;
    private int peopleNeedingAid;
    private String volunteerSkills;
    private String Address;
    private String description;
    private List<String> resourceNames;
    private List<Integer> quantities;

    // Getters and Setters

    public String getVictimUsername() { return victimUsername; }
    public void setVictimUsername(String victimUsername) { this.victimUsername = victimUsername; }

    public String getAddress() { return Address; }
    public void setAddress(String Address) { this.Address = Address; }


    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    public int getPeopleNeedingAid() { return peopleNeedingAid; }
    public void setPeopleNeedingAid(int peopleNeedingAid) { this.peopleNeedingAid = peopleNeedingAid; }

    public String getVolunteerSkills() { return volunteerSkills; }
    public void setVolunteerSkills(String volunteerSkills) { this.volunteerSkills = volunteerSkills; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public List<String> getResourceNames() { return resourceNames; }
    public void setResourceNames(List<String> resourceNames) { this.resourceNames = resourceNames; }

    public List<Integer> getQuantities() { return quantities; }
    public void setQuantities(List<Integer> quantities) { this.quantities = quantities; }
}
