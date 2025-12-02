package com.project.dal;

import com.project.model.CustomerOrder;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class CustomerOrderDAOImpl implements CustomerOrderDAO {

    private final JdbcTemplate jdbc;

    public CustomerOrderDAOImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    private RowMapper<CustomerOrder> mapper = new RowMapper<CustomerOrder>() {
        @Override
        public CustomerOrder mapRow(ResultSet rs, int rowNum) throws SQLException {
            CustomerOrder o = new CustomerOrder();
            o.setOrderId(rs.getInt("order_id"));
            o.setCustomerId(rs.getInt("customer_id"));
            o.setVariantId(rs.getInt("variant_id"));
            o.setQuantity(rs.getInt("quantity"));

            // Convert SQL DATETIME → LocalDateTime
            o.setOrderDate(rs.getTimestamp("order_date").toLocalDateTime());

            return o;
        }
    };

    @Override
    public CustomerOrder findById(int orderId) {
        String sql = "SELECT * FROM Customer_Order WHERE order_id = ?";
        List<CustomerOrder> list = jdbc.query(sql, mapper, orderId);
        return list.isEmpty() ? null : list.get(0);
    }

    @Override
    public List<CustomerOrder> findByCustomerId(int customerId) {
        String sql = "SELECT * FROM Customer_Order WHERE customer_id = ?";
        return jdbc.query(sql, mapper, customerId);
    }

    @Override
    public boolean insert(CustomerOrder order) {
        String sql = """
        
                INSERT INTO Customer_Order (customer_id, variant_id, quantity)
                VALUES (?, ?, ?)
        """;

        return jdbc.update(sql,
                order.getCustomerId(),
                order.getVariantId(),
                order.getQuantity()
        ) > 0;
    }

    @Override
    public boolean delete(int orderId) {
        String sql = "DELETE FROM Customer_Order WHERE order_id = ?";
        return jdbc.update(sql, orderId) > 0;
    }
}
