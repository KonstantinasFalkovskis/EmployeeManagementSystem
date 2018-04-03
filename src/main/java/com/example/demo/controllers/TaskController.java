/**
 * @Author - Falco Constantine
 * @date - 2018.04.03
 * @version - v.1.0
 */
package com.example.demo.controllers;

import com.example.demo.entities.Task;
import com.example.demo.services.TaskServiceImpl;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

/**
 * New task controller
 */
@Controller
public class TaskController {

    //Task services calling
    @Autowired
    private TaskServiceImpl taskServiceImpl;

    //Users services calling
    @Autowired
    private UserService userService;

    /**
     *
     * @param email
     * @param model
     * @param session
     * @return
     */
    @GetMapping("/addTask")
    public String taskForm(String email, Model model, HttpSession session) {
        session.setAttribute("email", email);
        model.addAttribute("task", new Task());
        return "views/taskForm";
    }

    /**
     * New task adding
     * @param task
     * @param bindingResult
     * @param session
     * @return
     */
    @PostMapping("/addTask")
    public String addTask(@Valid Task task, BindingResult bindingResult, HttpSession session) {
        if(bindingResult.hasErrors()) {
            return "views/taskForm";
        }
        String email = (String) session.getAttribute("email");
        taskServiceImpl.addTask(task, userService.findOne(email));
        return  "redirect:/users";
    }
}
