package com.project.store.controllers;

import com.project.dal.CartDAO;
import com.project.dal.ProductVariantDAO;
import com.project.enums.Color;
import com.project.enums.Size;
import com.project.model.Cart;
import com.project.model.ProductVariant;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@Controller
public class CartController {

    private final CartDAO cartDAO;
    private final ProductVariantDAO variantDAO;

    public CartController(CartDAO cartDAO, ProductVariantDAO variantDAO) {
        this.cartDAO = cartDAO;
        this.variantDAO = variantDAO;
    }

    @GetMapping("/cart")
    public String cart(Model model, HttpSession session) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) return "redirect:/login";

        List<Cart> items = cartDAO.findByCustomerId(customerId);

        // display product info
        List<Map<String, Object>> displayList = new ArrayList<>();

        for (Cart c : items) {
            ProductVariant pv = variantDAO.findById(c.getVariantId());
            Map<String, Object> map = new HashMap<>();
            map.put("cartId", c.getCartId());
            map.put("productId", pv.getProductId());
            map.put("color", pv.getColor());
            map.put("size", pv.getSize());

            displayList.add(map);
        }


        model.addAttribute("cartItems", displayList);
        return "cart";
    }

    @GetMapping("/cart/add")
    public String add(
            @RequestParam int variantId,
            HttpSession session
    ) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) return "redirect:/login";

        Cart c = new Cart(0, customerId, variantId);
        cartDAO.insert(c);

        return "redirect:/cart";
    }

    @GetMapping("/cart/delete")
    public String delete(@RequestParam int id) {
        cartDAO.delete(id);
        return "redirect:/cart";
    }

    @PostMapping("/cart/add")
    public String addToCart(
            @RequestParam int productId,
            @RequestParam String color,
            @RequestParam String size,
            HttpSession session
    ) {
        Integer customerId = (Integer) session.getAttribute("customerId");
        if (customerId == null) return "redirect:/login";

        // Get all variants for this product
        List<ProductVariant> variants = variantDAO.findByProductId(productId);

        // Try to find matching variant
        ProductVariant match = null;
        for (ProductVariant v : variants) {
            if (v.getColor().toDb().equals(color) &&
                    v.getSize().toDb().equals(size)) {
                match = v;
                break;
            }
        }

        // create variant selected by customer
        if (match == null) {
            match = new ProductVariant();
            match.setProductId(productId);

            // string --> enum
            match.setColor(Color.fromDb(color));
            match.setSize(Size.fromDb(size));

            match.setStock(1); // default stock for new variants

            variantDAO.save(match);

            // fetch new variant to get the variant_id
            List<ProductVariant> refreshed = variantDAO.findByProductId(productId);
            for (ProductVariant v : refreshed) {
                if (v.getColor().toDb().equals(color) &&
                        v.getSize().toDb().equals(size)) {
                    match = v;
                    break;
                }
            }
        }

        Cart c = new Cart(0, customerId, match.getVariantId());
        cartDAO.insert(c);

        //direct user back to browsing
        return "redirect:/products";
    }


}
