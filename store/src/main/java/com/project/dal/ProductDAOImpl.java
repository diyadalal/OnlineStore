package com.project.dal;

import com.project.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductDAOImpl implements ProductDAO {

    private final JdbcTemplate jdbc;

    @Autowired
    public ProductDAOImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    @Override
    public Product findById(int id) {
        String sql = "SELECT * FROM Product WHERE product_id = ?";
        return jdbc.queryForObject(sql, new ProductRowMapper(), id);
    }

    @Override
    public List<Product> findAll() {
        String sql = "SELECT * FROM Product ORDER BY product_id";
        return jdbc.query(sql, new ProductRowMapper());
    }

    @Override
    public boolean save(Product p) {
        String sql = """
            INSERT INTO Product (name, category, description, brand, price, sold)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        int rows = jdbc.update(sql,
                p.getName(),
                //p.getCategory(),
                p.getDescription(),
                p.getBrand(),
                //p.isSold(),
                p.getPrice()
        );

        return rows > 0;
    }

    @Override
    public boolean update(Product p) {
        String sql = """
            UPDATE Product
            SET name = ?, category = ?, description = ?, brand = ?, price = ?, sold = ?
            WHERE product_id = ?
        """;

        int rows = jdbc.update(sql,
                p.getName(),
                //p.getCategory(),
                p.getDescription(),
                p.getBrand(),
                p.getPrice(),
                //p.isSold(),
                p.getProductId()
        );

        return rows > 0;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Product WHERE product_id = ?";
        return jdbc.update(sql, id) > 0;
    }
}
