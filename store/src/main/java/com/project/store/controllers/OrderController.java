package com.project.store.controllers;

import com.project.dal.CustomerOrderDAO;
import com.project.dal.ProductVariantDAO;
import com.project.model.CustomerOrder;
import com.project.model.ProductVariant;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.*;

@Controller
public class OrderController {

    private final CustomerOrderDAO orderDAO;
    private final ProductVariantDAO variantDAO;

    public OrderController(CustomerOrderDAO orderDAO, ProductVariantDAO variantDAO) {
        this.orderDAO = orderDAO;
        this.variantDAO = variantDAO;
    }

    @GetMapping("/orders")
    public String orders(Model model, HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) return "redirect:/login";

        List<CustomerOrder> orders = orderDAO.findByCustomerId(customerId);

        // Add variant details for display
        List<Map<String, Object>> displayList = new ArrayList<>();

        for (CustomerOrder o : orders) {
            ProductVariant v = variantDAO.findById(o.getVariantId());

            Map<String, Object> map = new HashMap<>();
            map.put("orderId", o.getOrderId());
            map.put("variantId", v.getProductId());
            map.put("color", v.getColor());
            map.put("size", v.getSize());
            map.put("orderDate", o.getOrderDate());

            displayList.add(map);
        }

        model.addAttribute("orders", displayList);
        return "orders";
    }
}
