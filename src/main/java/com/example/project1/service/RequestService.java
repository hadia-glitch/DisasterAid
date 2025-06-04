package com.example.project1.service;

import com.example.project1.model.Request;
import com.example.project1.repository.RequestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RequestService {

    @Autowired
    private RequestRepository requestRepository;

    public void save(Request request) {
        requestRepository.save(request);
    }
    public List<Request> getAllRequestsOrderedByPriority() {
        return requestRepository.findAllByOrderByPriorityDesc();  // Ordering requests by priority
    }

    public Request getRequestById(Long requestId) {
        return requestRepository.findById(requestId).orElse(null);
    }

    public void saveRequest(Request request) {
        requestRepository.save(request);
    }
    public List<String> findResourcesByRequestId(Long requestId) {
        List<Request> requests = requestRepository.findByRequestId(requestId);
        List<String> resourceNames = new ArrayList<>();

        for (Request request : requests) {
            resourceNames.add(request.getResourceName());
        }

        return resourceNames;
    }

    public Long getNextRequestId() {
        return requestRepository.getNextRequestId();
    }
}
