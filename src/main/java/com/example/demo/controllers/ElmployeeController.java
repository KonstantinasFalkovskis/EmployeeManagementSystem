/**
 * @Author - Falco Constantine
 * @date - 2018.03.26
 * @version - v.1.0
 */
package com.example.demo.controllers;

import com.example.demo.entities.Employee;
import com.example.demo.services.DepartamentService;
import com.example.demo.services.EmployeeService;
import com.example.demo.services.EmployeeServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Pageable;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

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

    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    //get services for department, which here is as list of departments
    @Autowired
    private DepartamentService departamentService;

    @Value("${spring.datasource.url}")
    private String urldb;

    @Value("${spring.datasource.username}")
    private String userdb;

    @Value("${spring.datasource.password}")
    private String passdb;

    @Autowired
    public void setEmployeeServiceImpl(EmployeeServiceImpl employeeServiceImpl) {
        this.employeeServiceImpl = employeeServiceImpl;
    }

    /**
     * Searching & paging: calling searching form by name;
     * default rows per page 7
     * @param pageable
     * @param name
     * @param model
     * @return
     */

    @GetMapping("/employees")
    public String userList(Model model, @PageableDefault(size = 5) Pageable pageable,
                           @RequestParam(name = "name", required = false) String name) {
        if(name != null) {
            if(employeeServiceImpl.findByName(name).isEmpty() && !employeeServiceImpl.findByName(name).equals(name)) {
                model.addAttribute("exist", true);
            } else {
                model.addAttribute("employees", employeeServiceImpl.findByName(name, pageable));
                Page<Employee> userPage = employeeServiceImpl.findByName(name, pageable);
                PageWrapper page = new PageWrapper<Employee>(userPage, "/employees");
                model.addAttribute("employees", page.getContent());
                logger.info("User " + name + " founded");
                model.addAttribute("page", page);
                return "employee/employees";
            }
        }
        Page<Employee> userPage = employeeServiceImpl.findAll(pageable);
        PageWrapper page = new PageWrapper<Employee>(userPage, "/employees");
        model.addAttribute("employees", page.getContent());
        model.addAttribute("page", page);
        return "employee/employees";
    }

    /**
     * calling to adding new employee
     * @param model
     * @return
     */
    @RequestMapping("/employee/add")
    public String addEmployee(Model model) {
        model.addAttribute("employee", new Employee());
        model.addAttribute("departmens", departamentService.findListDepartment());
        logger.info("Employee added" + model);
        return "employee/employeeForm";
    }

    /**
     * Updating calling
     * @param id
     * @param model
     * @return
     */
    @GetMapping("employee/edit/{id}")
    public String updateEmploye(@PathVariable Long id, Model model) {
        model.addAttribute("employee", employeeService.getEmployeeById(id));
        model.addAttribute("departments", departamentService.findListDepartment());
        logger.info("Employee " + id + "updated" + model);
        return "employee/employeeForm";
    }

    /**
     * Employee viewing
     * @param id
     * @param model
     * @return
     */
    @GetMapping("employee/view/{id}")
  //  @RequestMapping(value = "employee/view/{id}", method = RequestMethod.GET)
    public String showEmployee(@PathVariable Long id, Model model, HttpSession session) {
            session.setAttribute("id", id);
           model.addAttribute("employee", employeeService.getEmployeeById(id));
           model.addAttribute("departaments", departamentService.findListDepartment());
        logger.info("Employee where id " + id + " opened");
        return "employee/employeeView";
    }

//    @GetMapping("/addTask")
//    public String taskForm(String email, Model model, HttpSession session) {
//        session.setAttribute("email", email);
//        model.addAttribute("task", new Task());
//        return "views/taskForm";
//    }
//
//    @PostMapping("/addTask")
//    public String addTask(@Valid Task task, BindingResult bindingResult, HttpSession session) {
//        if(bindingResult.hasErrors()) {
//            return "views/taskForm";
//        }
//        String email = (String) session.getAttribute("email");
//        taskServiceImpl.addTask(task, userService.findOne(email));
//        return  "redirect:/users";
//    }

    /**
     * Employee removing
     * @param id
     * @return
     */
    @RequestMapping("employee/delete/{id}")
    public String removeEmployee(@PathVariable Long id) {
        employeeService.deleteEmployee(id);
        logger.info("Employee " + id + " removed");
        return "redirect:/employees";
    }

    /**
     * Employee saving into the data base
     * @param employee
     * @return
     */
    @PostMapping("/employee")
    public String saveEmployee(BindingResult bindingResult, @Valid Employee employee, Model model) {
        if(bindingResult.hasErrors()) {
            return "employee/employeeForm";
        }
        if (employeeServiceImpl.isEmployeePresent(employee.getId())) {
            model.addAttribute("exist", true);
            return "employee/employeeForm";
        }
        employeeService.saveEmployee(employee);
        logger.info("Employee " + employee + " saved successfully into data base");
       // return  "redirect:/employees";
        return "employee/emplSuccess";
    }


}