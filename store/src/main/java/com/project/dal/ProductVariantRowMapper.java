package com.project.dal;

import com.project.enums.Color;
import com.project.enums.Size;
import com.project.model.ProductVariant;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductVariantRowMapper implements RowMapper<ProductVariant> {

    @Override
    public ProductVariant mapRow(ResultSet rs, int rowNum) throws SQLException {

        ProductVariant pv = new ProductVariant();
        pv.setVariantId(rs.getInt("variant_id"));
        pv.setProductId(rs.getInt("product_id"));
        pv.setColor(Color.fromDb(rs.getString("color_id")));
        pv.setSize(Size.fromDb(rs.getString("size_id")));
        pv.setStock(rs.getInt("stock"));

        return pv;
    }
}
