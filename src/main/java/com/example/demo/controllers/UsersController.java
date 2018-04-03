package com.example.demo.controllers;

import com.example.demo.entities.Employee;
import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class UsersController {

    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("/list")
    public String userList(Model model, @RequestParam(defaultValue = "") String name,
                           @PageableDefault(size = 10)Pageable pageable) {

        if(name != null) {
            model.addAttribute("users", userRepository.findByName(name, pageable));
            Page<User> userPage = userRepository.findByName(name, pageable);
            PageWrapper page = new PageWrapper(userPage, "/users");
            model.addAttribute("users", page.getContent());
            logger.info("User " + name + " founded");
            model.addAttribute("page", page);
            return "views/list";
        } else {
            Page<User> userPage = userRepository.findAll(pageable);
            PageWrapper page = new PageWrapper(userPage, "/users");
            model.addAttribute("employees", page.getContent());
            model.addAttribute("page", page);
            return "views/list";
        }
    }

    @GetMapping("/list/edit/{id}")
    public String updateUser(@PathVariable String email, Model model) {
         model.addAttribute("users", userService.findOne(email));
         logger.info("User " + email + " successfully updated");
         return "views/usersForm";
    }

    @GetMapping("/list/delete/{id}")
    public String removeUser(@PathVariable String email) {
       userRepository.deleteById(email);
       logger.info("User " + email + " successfully removed");
       return "redirect:list";
    }

    @PostMapping("/users/save")
    public String saveUser(User user) {
       userService.saveUser(user);
       logger.info("User " + user + " successfully saved");
      return "redirect:list";
    }

    @GetMapping("/users/view/{id}")
    public String showUser(@PathVariable String email, Model model) {
        model.addAttribute("users", userService.findOne(email));
        logger.info("User " + email + " successfully viewed");
        return "/views/usersForm";
    }
}