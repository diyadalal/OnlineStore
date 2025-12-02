package com.project.dal;

import com.project.model.ProductVariant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductVariantDAOImpl implements ProductVariantDAO {

    private final JdbcTemplate jdbc;

    @Autowired
    public ProductVariantDAOImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public ProductVariant findById(int id) {
        String sql = "SELECT * FROM Product_Variant WHERE variant_id = ?";
        return jdbc.queryForObject(sql, new ProductVariantRowMapper(), id);
    }

    @Override
    public List<ProductVariant> findByProductId(int productId) {
        String sql = "SELECT * FROM Product_Variant WHERE product_id = ? ORDER BY variant_id";
        return jdbc.query(sql, new ProductVariantRowMapper(), productId);
    }

    @Override
    public List<ProductVariant> findAll() {
        String sql = "SELECT * FROM Product_Variant ORDER BY variant_id";
        return jdbc.query(sql, new ProductVariantRowMapper());
    }

    @Override
    public boolean save(ProductVariant pv) {
        String sql = """
            INSERT INTO Product_Variant (product_id, color_id, size_id, stock)
            VALUES (?, ?, ?, ?)
        """;

        int rows = jdbc.update(sql,
                pv.getProductId(),
                pv.getColor(),
                pv.getSize(),
                pv.getStock()
        );

        return rows > 0;
    }

    @Override
    public boolean update(ProductVariant pv) {
        String sql = """
            UPDATE Product_Variant
            SET product_id = ?, color_id = ?, size_id = ?, stock = ?
            WHERE variant_id = ?
        """;

        int rows = jdbc.update(sql,
                pv.getProductId(),
                pv.getColor(),
                pv.getSize(),
                pv.getStock(),
                pv.getVariantId()
        );

        return rows > 0;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Product_Variant WHERE variant_id = ?";
        return jdbc.update(sql, id) > 0;
    }
}
