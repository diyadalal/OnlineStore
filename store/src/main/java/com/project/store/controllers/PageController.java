package com.project.store.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping("/")
    public String redirectToLogin() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String login() { return "login"; }

    @GetMapping("/products")
    public String products() { return "products"; }

    @GetMapping("/product")
    public String product() { return "product"; }

    @GetMapping("/cart")
    public String cart() { return "cart"; }

}


