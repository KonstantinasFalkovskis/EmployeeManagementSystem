package com.example.demo.controllers;

import com.example.demo.entities.Departament;
import com.example.demo.repository.DepartamentRepository;
import com.example.demo.services.DepartamentService;
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
public class DepartmentController {

    @Autowired
    private DepartamentService departamentService;

    @Autowired
    private DepartamentRepository departamentRepository;

    @GetMapping("/departments")
    public String list(@PageableDefault(size = 7) Pageable pageable,
                       @RequestParam(defaultValue = "") String department, Model model) {

        if(department != null) {
            model.addAttribute("depo", departamentService.findDepoByName(department, pageable));
            Page<Departament> depoPage = departamentService.findDepoByName(department, pageable);
            PageWrapper page = new PageWrapper(depoPage, "/departments");
            model.addAttribute("departments", page.getContent());
            model.addAttribute("page", page);
            return "views/departments";
        } else {
            Page<Departament> depoPage = departamentService.findAll(pageable);
            PageWrapper page = new PageWrapper(depoPage, "/departments");
            model.addAttribute("departments", page.getContent());
            model.addAttribute("page", page);
            return "views/departments";
        }
    }

    @GetMapping("/add")
    public String newDepo(Model model) {
        model.addAttribute("department", new Departament());
        return "views/depoForm";
    }

    @GetMapping("/edit/{id}")
    public String updateDepo(@PathVariable Long id, Model model) {
        model.addAttribute("department", departamentService.findOne(id));
        return "views/depoForm";
    }

    @GetMapping("/view/{id}")
    public String showDepo(@PathVariable Long id, Model model) {
        model.addAttribute("department", departamentService.findOne(id));
        return "views/showForm";
    }

    @PostMapping("/save")
    public String saveDepo(Model model, Departament departament) {
        model.addAttribute("department", departamentService.saveDepo(departament));
        return "views/departments";
    }

    

}
