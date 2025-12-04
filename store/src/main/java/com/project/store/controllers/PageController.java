package com.project.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {
    //redirects user to the login page when they run the application
    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }
}
