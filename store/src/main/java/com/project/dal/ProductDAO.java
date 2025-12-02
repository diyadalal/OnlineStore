package com.project.dal;

import com.project.model.Product;
import java.util.List;

public interface ProductDAO {
    Product findById(int id);
    List<Product> findAll();
    boolean save(Product product);
    boolean update(Product product);
    boolean delete(int id);
}
