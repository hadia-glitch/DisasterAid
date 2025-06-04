package com.example.project1.service;

import com.example.project1.model.Resource;
import com.example.project1.repository.ResourceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ResourceService {

    private final ResourceRepository resourceRepository;

    @Autowired
    public ResourceService(ResourceRepository resourceRepository) {
        this.resourceRepository = resourceRepository;
    }

    public List<Resource> getAllResources() {
        return resourceRepository.findAll();
    }

    public void saveResource(Resource resource) {
        resourceRepository.save(resource);
    }

    public void updateResourceQuantity(String resourceName, int quantityToAdd) {
        Optional<Resource> optionalResource = resourceRepository.findByName(resourceName);
        if (optionalResource.isPresent()) {
            Resource existing = optionalResource.get();
            int updatedQuantity = existing.getQuantity() + quantityToAdd;
            existing.setQuantity(updatedQuantity);
            resourceRepository.save(existing);
        } else {
            Resource newResource = new Resource(resourceName, quantityToAdd);
            resourceRepository.save(newResource);
        }
    }

    public Resource getById(Long id) {
        Optional<Resource> optionalResource = resourceRepository.findById(id);
        return optionalResource.orElse(null); // return null if not found (or throw exception)
    }
}
