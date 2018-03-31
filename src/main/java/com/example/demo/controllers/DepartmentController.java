/**
 * @Author - Falco Constantine
 * @date - 2018.03.29
 * @version - v.1.0
 */
package com.example.demo.controllers;

import com.example.demo.entities.Departament;
import com.example.demo.repository.DepartamentRepository;
import com.example.demo.services.DepartamentService;
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

import java.util.List;

/**
 * Department controller
 */
@Controller
public class DepartmentController {

    private static final Logger logger = LoggerFactory.getLogger(DepartmentController.class);

    //department service class calling
    @Autowired
    private DepartamentService departamentService;

    /**
     * searching and paging
     * @param pageable
     * @param department
     * @param model
     * @return
     */
    @GetMapping("/departments")
    public String list(@PageableDefault(size = 7) Pageable pageable,
                       @RequestParam(defaultValue = "") String department, Model model) {

        if(department != null) {
            model.addAttribute("depo", departamentService.findDepoByName(department, pageable));
            Page<Departament> depoPage = departamentService.findDepoByName(department, pageable);
            PageWrapper page = new PageWrapper(depoPage, "/departments");
            model.addAttribute("departments", page.getContent());
            model.addAttribute("page", page);
            logger.info("Department " + department + " founded");
            return "views/departments";
        } else {
            Page<Departament> depoPage = departamentService.findAll(pageable);
            PageWrapper page = new PageWrapper(depoPage, "/departments");
            model.addAttribute("departments", page.getContent());
            model.addAttribute("page", page);
            Iterable<Departament> depoList = departamentService.findListDepartment();
            for(Departament departament: depoList){
                logger.info("Department " + departament + " goes to the list successfully");
            }
            return "views/departments";
        }
    }

    /**
     * Adding new department
     * @param model
     * @return
     */
    @GetMapping("departments/add")
    public String newDepo(Model model) {
        model.addAttribute("department", new Departament());
        logger.info("Department " + model + "added successfully");
        return "views/depoForm";
    }

    /**
     * Updating department
     * @param id
     * @param model
     * @return
     */
    @GetMapping("departments/edit/{id}")
    public String updateDepo(@PathVariable Long id, Model model) {
        model.addAttribute("department", departamentService.findOne(id));
        logger.info("Department " + id + model + " successfully edited");
        return "views/depoForm";
    }

    /**
     * Viewing department
     * @param id
     * @param model
     * @return
     */
    @GetMapping("departments/view/{id}")
    public String showDepo(@PathVariable Long id, Model model) {
        model.addAttribute("department", departamentService.findOne(id));
        logger.info("Department " + id + model + " viewed successfully");
        return "views/showForm";
    }

    /**
     * Saving new department into the data base
     * @param model
     * @param departament
     * @return
     */
    @PostMapping("departments/save")
    public String saveDepo(Model model, Departament departament) {
        model.addAttribute("department", departamentService.saveDepo(departament));
        logger.info("Department " + departament + " saved successfully");
        return "views/departments";
    }

    /**
     * Removing department
     * @param id
     * @return
     */
    @GetMapping("departments/delete/{id}")
    public String removeDepo(@PathVariable Long id) {
        if(departamentService.findOne(id)!= null){
            departamentService.deleteDepo(id);
        }
        logger.info("Department " + id + " removed successfully");
        return "views/departments";
    }
}
