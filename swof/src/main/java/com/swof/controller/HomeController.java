package com.swof.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author vipin
 * @since 03/18/2018
 */
@Controller
public class HomeController {

    @RequestMapping("/home")
    public String viewHome() {
    	 return "home";
    }
}
