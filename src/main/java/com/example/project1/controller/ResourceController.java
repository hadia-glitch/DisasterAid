package com.example.project1.controller;

import com.example.project1.model.Admin;
import com.example.project1.model.Resource;
import com.example.project1.repository.AdminRepository;
import com.example.project1.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/resources")
public class ResourceController {

    private final ResourceService resourceService;
    @Autowired
    private AdminRepository adminRepository;    @Autowired
    public ResourceController(ResourceService resourceService) {
        this.resourceService = resourceService;
    }

    // Display the resources page: table + forms
    @GetMapping
    public String showResources(@RequestParam("username") String username,Model model) {
        Admin admin = adminRepository.findByUsername(username);
        model.addAttribute("admin", admin);
        List<Resource> resources = resourceService.getAllResources();
        model.addAttribute("resources", resources);
        return "resources";   // resolves to resources.html
    }

    // Handle form to add a new resource
    @PostMapping("/save")
    public String saveResource(@RequestParam("username") String username,Model model,
            @RequestParam("name") String name,
            @RequestParam("quantity") int quantity
    ) {
        Admin admin = adminRepository.findByUsername(username);
        model.addAttribute("admin", admin);
        Resource r = new Resource();
        r.setName(name); // <-- not setType
        r.setQuantity(quantity);
        resourceService.saveResource(r);
        return "redirect:/admin/resources?username="+username;

    }


    // Handle form to update existing resource quantity
    @PostMapping("/update")
    public String updateResourceQuantity(
            @RequestParam("username") String username,Model model,
            @RequestParam("resourceName") String resourceName,
            @RequestParam("quantityToAdd") int quantityToAdd
    ) { Admin admin = adminRepository.findByUsername(username);
        model.addAttribute("admin", admin);
        resourceService.updateResourceQuantity(resourceName, quantityToAdd);
        return "redirect:/admin/resources?username="+username;
    }
}
