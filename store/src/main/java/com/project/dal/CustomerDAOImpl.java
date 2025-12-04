package com.project.dal;

import com.project.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class CustomerDAOImpl implements CustomerDAO {

    private final JdbcTemplate jdbc;
//JDBC constructor
    @Autowired
    public CustomerDAOImpl(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }
//Maps customer rows from database to a java object
    private final RowMapper<Customer> customerMapper = new RowMapper<>() {
        @Override
        public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
            Customer c = new Customer();
            c.setCustomerId(rs.getInt("customer_id"));
            c.setFirstName(rs.getString("first_name"));
            c.setLastName(rs.getString("last_name"));
            c.setEmail(rs.getString("email"));
            c.setAddress(rs.getString("address"));
            c.setBalance(rs.getBigDecimal("balance"));
            c.setPasswordHash(rs.getString("password_hash"));
            return c;
        }
    };
//Using SQL statements to perform Java functions on customers
    @Override
    public Customer findById(int id) {
        String sql = "SELECT * FROM Customer WHERE customer_id = ?";
        List<Customer> result = jdbc.query(sql, customerMapper, id);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public Customer findByEmail(String email) {
        String sql = "SELECT * FROM Customer WHERE email = ?";
        List<Customer> result = jdbc.query(sql, customerMapper, email);
        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT * FROM Customer";
        return jdbc.query(sql, customerMapper);
    }

    @Override
    public boolean save(Customer customer) {
        String sql = """
            INSERT INTO Customer
                (first_name, last_name, email, address, balance, password_hash)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        int rows = jdbc.update(sql,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getBalance(),
                customer.getPasswordHash()
        );

        return rows > 0;
    }

    @Override
    public boolean update(Customer customer) {
        String sql = """
            UPDATE Customer
            SET first_name = ?, last_name = ?, email = ?, address = ?, balance = ?, password_hash = ?
            WHERE customer_id = ?
        """;

        int rows = jdbc.update(sql,
                customer.getFirstName(),
                customer.getLastName(),
                customer.getEmail(),
                customer.getAddress(),
                customer.getBalance(),
                customer.getPasswordHash(),
                customer.getCustomerId()
        );

        return rows > 0;
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Customer WHERE customer_id = ?";
        return jdbc.update(sql, id) > 0;
    }
}
