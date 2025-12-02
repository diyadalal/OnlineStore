package com.project.store.controllers;

import com.project.dal.CartDAO;
import com.project.dal.CustomerOrderDAO;
import com.project.dal.ProductVariantDAO;
import com.project.model.Cart;
import com.project.model.CustomerOrder;
import com.project.model.ProductVariant;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class CheckoutController {

    private final CartDAO cartDAO;
    private final ProductVariantDAO variantDAO;
    private final CustomerOrderDAO orderDAO;

    public CheckoutController(
            CartDAO cartDAO,
            ProductVariantDAO variantDAO,
            CustomerOrderDAO orderDAO
    ) {
        this.cartDAO = cartDAO;
        this.variantDAO = variantDAO;
        this.orderDAO = orderDAO;
    }

    @GetMapping("/checkout")
    public String checkout(HttpSession session) {

        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) return "redirect:/login";

        // Get all items in cart
        List<Cart> items = cartDAO.findByCustomerId(customerId);

        for (Cart c : items) {
            // get variant info
            ProductVariant pv = variantDAO.findById(c.getVariantId());

            // adjust stock
            pv.setStock(pv.getStock() - 1);
            variantDAO.update(pv);

            // create order for bought item
            CustomerOrder order = new CustomerOrder();
            order.setCustomerId(customerId);
            order.setVariantId(c.getVariantId());
            order.setQuantity(1);
            order.setOrderDate(LocalDateTime.now());

            orderDAO.insert(order);
        }

        // clear cart after item is bought
        cartDAO.clearCustomerCart(customerId);

        return "redirect:/orders";
    }
}
