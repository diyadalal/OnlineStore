package com.project.dal;

import com.project.model.Cart;
import java.util.List;

public interface CartDAO {
    Cart findById(int cartId);
    List<Cart> findByCustomerId(int customerId);
    boolean insert(Cart cart);
    boolean delete(int cartId);
    boolean clearCustomerCart(int customerId);
}
