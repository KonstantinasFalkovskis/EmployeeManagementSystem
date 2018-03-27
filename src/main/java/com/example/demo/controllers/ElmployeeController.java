package com.example.demo.controllers;

import com.example.demo.entities.Employee;
import com.example.demo.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.data.domain.Pageable;

@Controller
public class ElmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/empoyees")
    public String list(@PageableDefault(size = 6) Pageable pageable,
                               @RequestParam(name = "name", required = false) String name, Model model) {

        if(name != null) {
            model.addAttribute("key", name);
            Page<Employee> employeePage = employeeService.findByName(name, pageable);
            PageWrapper page = new PageWrapper<Employee>(employeePage, "/employees");
            model.addAttribute("employees", page.getContent());
            model.addAttribute("page", page);
            return "views/employees";
        } else {
            Page<Employee> employeePage = employeeService.findAll(pageable);
            PageWrapper page = new PageWrapper(employeePage, "/employees");
            model.addAttribute("employees", page.getContent());
            model.addAttribute("page", page);
            return "views/emplyees";
        }

    }


}
