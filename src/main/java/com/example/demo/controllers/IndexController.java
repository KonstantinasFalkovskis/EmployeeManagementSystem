/**
 * @Author - Falco Constantine
 * @date - 2018.03.28
 * @version - v.1.0
 */
package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

//controller for index.html calling
@Controller
public class IndexController {

    @RequestMapping(name = "/", method = RequestMethod.GET)
    public String showPage() {
        return "index";
    }
}
