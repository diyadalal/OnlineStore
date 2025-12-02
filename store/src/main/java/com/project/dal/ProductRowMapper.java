package com.project.dal;

import com.project.model.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {

        Product p = new Product();
        p.setProductId(rs.getInt("product_id"));
        p.setName(rs.getString("name"));
        //p.setCategory(rs.getString("category"));
        p.setDescription(rs.getString("description"));
        p.setBrand(rs.getString("brand"));
        p.setPrice(rs.getBigDecimal("price"));
        //p.setSold(rs.getBoolean("sold"));

        return p;
    }
}
