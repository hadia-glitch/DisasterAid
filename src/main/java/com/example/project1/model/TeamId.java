package com.example.project1.model;

import java.io.Serializable;
import java.util.Objects;

public class TeamId implements Serializable {
    private Long requestId;
    private String volunteerUsername;

    public TeamId() {}

    public TeamId(Long requestId, String volunteerUsername) {
        this.requestId = requestId;
        this.volunteerUsername = volunteerUsername;
    }

    // equals and hashCode are REQUIRED for composite keys
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TeamId)) return false;
        TeamId teamId = (TeamId) o;
        return Objects.equals(requestId, teamId.requestId) &&
                Objects.equals(volunteerUsername, teamId.volunteerUsername);
    }

    @Override
    public int hashCode() {
        return Objects.hash(requestId, volunteerUsername);
    }

    // Getters and setters (optional, but good practice)
    public Long getRequestId() { return requestId; }
    public void setRequestId(Long requestId) { this.requestId = requestId; }

    public String getVolunteerUsername() { return volunteerUsername; }
    public void setVolunteerUsername(String volunteerUsername) { this.volunteerUsername = volunteerUsername; }
}
