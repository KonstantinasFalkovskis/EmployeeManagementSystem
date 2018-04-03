package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.services.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegisterController {

    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping("/register")
    public String registerForm(Model model) {
        model.addAttribute("user", new User());
        return "views/registerForm";
    }

    @PostMapping("/register")
    public String registerUser(BindingResult bindingResult, @Valid User user, Model model) {
        if(bindingResult.hasErrors()) {
            return "views/registerForm";
        }
        if (userServiceImpl.isUserPresent(user.getEmail())) {
            model.addAttribute("exist", true);
            return "views/registerForm";
        }
        userServiceImpl.createUser(user);
        return "views/success";
    }
}
