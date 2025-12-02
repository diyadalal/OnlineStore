package com.project.dal;

import com.project.model.Cart;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CartDAOImpl implements CartDAO {

    private final JdbcTemplate jdbc;

    public CartDAOImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private RowMapper<Cart> mapper = new RowMapper<Cart>() {
        @Override
        public Cart mapRow(ResultSet rs, int rowNum) throws SQLException {
            Cart c = new Cart();
            c.setCartId(rs.getInt("cart_id"));
            c.setCustomerId(rs.getInt("customer_id"));
            c.setVariantId(rs.getInt("variant_id"));
            return c;
        }
    };

    @Override
    public Cart findById(int cartId) {
        String sql = "SELECT * FROM Cart WHERE cart_id = ?";
        List<Cart> result = jdbc.query(sql, mapper, cartId);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<Cart> findByCustomerId(int customerId) {
        String sql = "SELECT * FROM Cart WHERE customer_id = ?";
        return jdbc.query(sql, mapper, customerId);
    }

    @Override
    public boolean insert(Cart cart) {
        String sql = """
            INSERT INTO Cart (customer_id, variant_id)
            VALUES (?, ?)
        """;

        return jdbc.update(sql,
                cart.getCustomerId(),
                cart.getVariantId()
        ) > 0;
    }

    @Override
    public boolean delete(int cartId) {
        String sql = "DELETE FROM Cart WHERE cart_id = ?";
        return jdbc.update(sql, cartId) > 0;
    }

    @Override
    public boolean clearCustomerCart(int customerId) {
        String sql = "DELETE FROM Cart WHERE customer_id = ?";
        return jdbc.update(sql, customerId) > 0;
    }
}
