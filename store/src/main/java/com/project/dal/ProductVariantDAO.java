package com.project.dal;

import com.project.model.ProductVariant;
import java.util.List;

public interface ProductVariantDAO {
    ProductVariant findById(int id);
    List<ProductVariant> findByProductId(int productId);
    List<ProductVariant> findAll();
    boolean save(ProductVariant pv);
    boolean update(ProductVariant pv);
    boolean delete(int id);
}
