package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.repository.RoleRepository;
import com.example.demo.services.TaskServiceImpl;
import com.example.demo.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;

@Controller
public class ProfileController {

    @Autowired
    private TaskServiceImpl taskServiceImpl;

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/profile")
    public String showProfile(Model model, Principal principal) {
        String email =principal.getName();
        User user = userServiceImpl.findOne(email);
        model.addAttribute("task", taskServiceImpl.findByUser(user));
        return "views/profile";
    }
}
