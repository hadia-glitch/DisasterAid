package com.example.project1.controller;

import com.example.project1.model.Request;
import com.example.project1.service.RequestService;
import com.example.project1.service.ResourceService;
import com.example.project1.wrapper.RequestWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/victim")
public class RequestController {

    @Autowired
    private RequestService requestService;

    @Autowired
    private ResourceService resourceService;

    @GetMapping("/submit-request")
    public String showRequestForm(@RequestParam String username, Model model) {
        RequestWrapper wrapper = new RequestWrapper();
        wrapper.setVictimUsername(username); // Set it into the form-backing object

        model.addAttribute("wrapper", wrapper);
        model.addAttribute("victimUsername", username); // Send separately in case needed
        model.addAttribute("resources", resourceService.getAllResources());
        return "request_form";
    }

    @PostMapping("/submit-request")
    public String submitRequest(@ModelAttribute RequestWrapper wrapper) {
        Long newRequestId = requestService.getNextRequestId();

        for (int i = 0; i < wrapper.getResourceNames().size(); i++) {
            Request req = new Request();
            req.setRequestId(newRequestId);
            req.setVictimUsername(wrapper.getVictimUsername());
            req.setPriority(wrapper.getPriority());
            req.setPeopleNeedingAid(wrapper.getPeopleNeedingAid());
            req.setVolunteerSkills(wrapper.getVolunteerSkills());
            req.setAddress(wrapper.getAddress());
            req.setDescription(wrapper.getDescription());
            req.setResourceName(wrapper.getResourceNames().get(i));
            req.setQuantityRequested(wrapper.getQuantities().get(i));
            req.setStatus("pending");
            requestService.save(req);
        }

        return "redirect:/victim/submit-request?username=" + wrapper.getVictimUsername() + "&success";
    }
}
