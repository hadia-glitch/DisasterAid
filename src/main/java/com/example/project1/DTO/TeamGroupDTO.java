package com.example.project1.DTO;

import java.util.Map;
import java.util.Set;

public class TeamGroupDTO {

    private Long requestId;
    private Set<String> volunteerUsernames;
    private Map<String, Integer> resourceQuantityMap;
    private Set<String> addresses;
    private Set<String> taskDescriptions;

    public TeamGroupDTO(Long requestId, Set<String> volunteerUsernames, Map<String, Integer> resourceQuantityMap,
                        Set<String> addresses, Set<String> taskDescriptions) {
        this.requestId = requestId;
        this.volunteerUsernames = volunteerUsernames;
        this.resourceQuantityMap = resourceQuantityMap;
        this.addresses = addresses;
        this.taskDescriptions = taskDescriptions;
    }

    public Long getRequestId() {
        return requestId;
    }

    public Set<String> getVolunteerUsernames() {
        return volunteerUsernames;
    }

    public Map<String, Integer> getResourceQuantityMap() {
        return resourceQuantityMap;
    }

    public Set<String> getAddresses() {
        return addresses;
    }

    public Set<String> getTaskDescriptions() {
        return taskDescriptions;
    }

    public void setRequestId(Long requestId) {
        this.requestId = requestId;
    }

    public void setVolunteerUsernames(Set<String> volunteerUsernames) {
        this.volunteerUsernames = volunteerUsernames;
    }

    public void setResourceQuantityMap(Map<String, Integer> resourceQuantityMap) {
        this.resourceQuantityMap = resourceQuantityMap;
    }

    public void setAddresses(Set<String> addresses) {
        this.addresses = addresses;
    }

    public void setTaskDescriptions(Set<String> taskDescriptions) {
        this.taskDescriptions = taskDescriptions;
    }
}
