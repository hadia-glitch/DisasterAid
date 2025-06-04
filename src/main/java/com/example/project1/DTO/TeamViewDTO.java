package com.example.project1.DTO;


public class TeamViewDTO {
    private Long teamId;
    private Long requestId;
    private String address;
    private String taskDescription;
    private String volunteerUsername;
    private String resourceName;
    private int quantity;

    public TeamViewDTO(Long teamId, Long requestId, String address, String taskDescription,
                       String volunteerUsername, String resourceName, int quantity) {
        this.teamId = teamId;
        this.requestId = requestId;
        this.address = address;
        this.taskDescription = taskDescription;
        this.volunteerUsername = volunteerUsername;
        this.resourceName = resourceName;
        this.quantity = quantity;
    }

    public Long getTeamId() { return teamId; }
    public Long getRequestId() { return requestId; }
    public String getAddress() { return address; }
    public String getTaskDescription() { return taskDescription; }
    public String getVolunteerUsername() { return volunteerUsername; }
    public String getResourceName() { return resourceName; }
    public int getQuantity() { return quantity; }
}
