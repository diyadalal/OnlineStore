package com.project.store.controllers;

import com.project.dal.ProductDAO;
import com.project.dal.ProductVariantDAO;
import com.project.model.Product;
import com.project.enums.Category;
import com.project.model.ProductVariant;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Controller
public class ProductController {

    private final ProductDAO productDAO;
    private final ProductVariantDAO variantDAO;

    public ProductController(ProductDAO productDAO, ProductVariantDAO variantDAO) {
        this.productDAO = productDAO;
        this.variantDAO = variantDAO;
    }
    //displays products from the database 
    @GetMapping("/products")
    public String listProducts(
            @RequestParam(required = false) String category,
            Model model) {

        List<Product> products;

        if (category == null || category.isBlank()) {
            products = productDAO.findAll();
        } else {
            Category catEnum = Category.fromDb(category);
            products = productDAO.findByCategory(catEnum);
            model.addAttribute("selectedCategory", category);
        }

        model.addAttribute("products", products);
        model.addAttribute("categories", Category.values());

        return "products";
    }

    //display details for a specific product
    @GetMapping("/product")
    public String product(@RequestParam int id, Model model) {
        Product p = productDAO.findById(id);
        if (p == null) return "redirect:/products";

        model.addAttribute("product", p);
        model.addAttribute("productId", id);

        model.addAttribute("colors", com.project.enums.Color.values());
        model.addAttribute("sizes", com.project.enums.Size.values());

        return "product";
    }


}
