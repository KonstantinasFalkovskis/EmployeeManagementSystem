/**
 * @Author - Falco Constantine
 * @date - 2018.03.26
 * @version - v.1.0
 */
package com.example.demo.controllers;

import com.example.demo.entities.Employee;
import com.example.demo.services.DepartamentService;
import com.example.demo.services.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

/**
 * Controller for Employee
 */
@Controller
public class ElmployeeController {

    //Logger for functionality checking. Logs info goes to console;
    private static final Logger logger = LoggerFactory.getLogger(ElmployeeController.class);

    //get services for employee
    @Autowired
    private EmployeeService employeeService;

    //get services for department, which here is as list of departments
    @Autowired
    private DepartamentService departamentService;

    /**
     * Searching & paging: calling searching form by name;
     * default rows per page 7
     * @param pageable
     * @param name
     * @param model
     * @return
     */
    @GetMapping("/empoyees")
    public String list(@PageableDefault(size = 7) Pageable pageable,
                               @RequestParam(defaultValue = "") String name, Model model) {

        if(name != null) {
            model.addAttribute("users", employeeService.findByName(name, pageable));
            Page<Employee> employeePage = employeeService.findByName(name, pageable);
            PageWrapper page = new PageWrapper(employeePage, "/employees");
            model.addAttribute("employees", page.getContent());
            logger.info("Employee " + name + " founded");
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

    /**
     * calling to adding new employee
     * @param model
     * @return
     */
    @RequestMapping("employees/add")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departmens", departamentService.findListDepartment());
        logger.info("Employee added" + model);
        return "views/employeeForm";
    }

    /**
     * Updating calling
     * @param id
     * @param model
     * @return
     */
    @GetMapping("employees/edit/{id}")
    public String updateEmploye(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        model.addAttribute("departments", departamentService.findListDepartment());
        logger.info("Employee " + id + "updated" + model);
        return "views/employeeForm";
    }

    /**
     * Employee viewing
     * @param id
     * @param model
     * @return
     */
    @GetMapping("employees/view/{id}")
    public String showEmployee(@PathVariable Long id, Model model) {
           model.addAttribute("employee", employeeService.getEmployeeById(id));
           model.addAttribute("departments", departamentService.findListDepartment());
        logger.info("Employee " + id + " opened");
        return "views/employeeShowForm";
    }

    /**
     * Employee removing
     * @param id
     * @return
     */
    @RequestMapping("employees/delete/{id}")
    public String removeEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        logger.info("Eployee " + id + " removed");
        return "redirect:/employees";
    }

    /**
     * Employee saving into the data base
     * @param employee
     * @return
     */
    @PostMapping("employees/save")
    public String saveEmployee(Employee employee) {
        employeeService.saveEmployee(employee);
        logger.info("Employee " + employee + " saved successfully into data base");
        return  "redirect:/employees";
    }

}