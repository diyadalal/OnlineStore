package com.project.dal;

import com.project.model.Customer;
import java.util.List;

public interface CustomerDAO {
    Customer findById(int id);

    Customer findByEmail(String email);

    List<Customer> findAll();

    boolean save(Customer customer);

    boolean update(Customer customer);

    boolean delete(int id);
}
