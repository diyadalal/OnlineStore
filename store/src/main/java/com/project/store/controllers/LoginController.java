package com.project.store.controllers;

import com.project.dal.CustomerDAO;
import com.project.model.Customer;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@Controller
public class LoginController {

    private final CustomerDAO customerDAO;

    public LoginController(CustomerDAO customerDAO) {
        this.customerDAO = customerDAO;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(
            @RequestParam String firstName,
            @RequestParam String lastName,
            @RequestParam String email,
            @RequestParam String password,
            HttpSession session
    ) {
        // Step 1: Check if the user already exists
        Customer existing = customerDAO.findByEmail(email);

        if (existing != null) {
            // Log them in
            session.setAttribute("customerId", existing.getCustomerId());
            return "redirect:/products";
        }

        // Step 2: Create a new user
        Customer newCustomer = new Customer();
        newCustomer.setFirstName(firstName);
        newCustomer.setLastName(lastName);
        newCustomer.setEmail(email);
        newCustomer.setAddress("N/A"); // You can change this later
        newCustomer.setBalance(BigDecimal.ZERO);
        newCustomer.setPasswordHash(password);

        customerDAO.save(newCustomer);

        // Step 3: Retrieve saved customer with generated ID
        Customer saved = customerDAO.findByEmail(email);

        // Step 4: Log them in
        session.setAttribute("customerId", saved.getCustomerId());

        return "redirect:/products";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login";
    }
}
