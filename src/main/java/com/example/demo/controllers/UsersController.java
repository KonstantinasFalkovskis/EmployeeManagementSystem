/**
 * @Author - Falco Constantine
 * @date - 2018.04.03
 * @version - v.1.0
 */
package com.example.demo.controllers;

import com.example.demo.entities.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.services.UserService;
import com.example.demo.services.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * Users controller
 */
@Controller
public class UsersController {

    //logger to input info toto console
    private static final Logger logger = LoggerFactory.getLogger(UsersController.class);

    //users services calling
    @Autowired
    private UserService userService;

    @Autowired
    private UserServiceImpl userServiceImpl;

    //users repository calling
    @Autowired
    private UserRepository userRepository;

    /**
     * users searching by name or users list calling if name is blank
     * @param model
     * @param name
     * @param pageable
     * @return
     */
    @GetMapping("/users")
    public String userList(Model model, @PageableDefault(size = 5) Pageable pageable,
                           @RequestParam(name = "name", required = false) String name) {
           if(name != null) {
               if(userServiceImpl.findByName(name).isEmpty() && !userServiceImpl.findByName(name).equals(name)) {
               model.addAttribute("exist", true);
           } else {
                   model.addAttribute("users", userServiceImpl.findByName(name, pageable));
                   Page<User> userPage = userServiceImpl.findByName(name, pageable);
                   PageWrapper page = new PageWrapper(userPage, "/users");
                   model.addAttribute("users", page.getContent());
                   logger.info("User " + name + " founded");
                   model.addAttribute("page", page);
                   return "views/users";
               }
        }
            Page<User> userPage = userServiceImpl.findAll(pageable);
            PageWrapper page = new PageWrapper(userPage, "/users");
            model.addAttribute("users", page.getContent());
            model.addAttribute("page", page);
            return "views/users";
    }

    /**
     * Existing user editing
     * @param email
     * @param model
     * @return
     */
    @GetMapping("/userView")
    public String updateUser(@PathVariable String email, Model model, HttpSession session) {
         model.addAttribute("users", userService.findOne(email));
         logger.info("User " + email + " successfully updated");
         return "views/usersShowForm";
    }

    /**
     * Exist user removing
     * @param email
     * @return
     */
    @GetMapping("/users/delete/{id}")
    public String removeUser(@PathVariable String email) {
       userRepository.deleteById(email);
       logger.info("User " + email + " successfully removed");
       return "redirect:users";
    }

    /**
     * added user saving into database
     * @param user
     * @return
     */
    @PostMapping("/users/save")
    public String saveUser(User user) {
       userService.saveUser(user);
       logger.info("User " + user + " successfully saved");
      return "redirect:users";
    }

    /**
     * exist user showing
     * @param email
     * @param model
     * @return
     */
    @GetMapping("/usersShowForm")
    public String showUser(@PathVariable String email, Model model) {
        model.addAttribute("users", userService.findOne(email));
        logger.info("User " + email + " successfully viewed");
        return "views/usersShowForm";
    }
}