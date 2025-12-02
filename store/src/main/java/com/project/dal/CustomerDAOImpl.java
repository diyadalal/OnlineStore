
package com.project.dal;

import com.project.model.Customer;
import com.project.util.DataSourceFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {

    @Override
    public Customer findById(int id) {
        String sql = "SELECT * FROM Customer WHERE customer_id = ?";

        try (Connection conn = DataSourceFactory.get().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapRow(rs);
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching customer by ID", e);
        }
    }

    @Override
    public Customer findByEmail(String email) {
        String sql = "SELECT * FROM Customer WHERE email = ?";

        try (Connection conn = DataSourceFactory.get().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, email);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return mapRow(rs);
            }
            return null;

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching customer by email", e);
        }
    }

    @Override
    public List<Customer> findAll() {
        String sql = "SELECT * FROM Customer";
        List<Customer> list = new ArrayList<>();

        try (Connection conn = DataSourceFactory.get().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                list.add(mapRow(rs));
            }

            return list;

        } catch (SQLException e) {
            throw new RuntimeException("Error fetching all customers", e);
        }
    }

    @Override
    public boolean save(Customer c) {
        String sql = """
            INSERT INTO Customer(first_name, last_name, email, address, balance, password_hash)
            VALUES (?, ?, ?, ?, ?, ?)
        """;

        try (Connection conn = DataSourceFactory.get().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setString(1, c.getFirstName());
            stmt.setString(2, c.getLastName());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getAddress());
            stmt.setBigDecimal(5, c.getBalance());
            stmt.setString(6, c.getPasswordHash());

            int affected = stmt.executeUpdate();

            if (affected > 0) {
                ResultSet keys = stmt.getGeneratedKeys();
                if (keys.next()) {
                    c.setCustomerId(keys.getInt(1));
                }
                return true;
            }
            return false;

        } catch (SQLException e) {
            throw new RuntimeException("Error saving customer", e);
        }
    }

    @Override
    public boolean update(Customer c) {
        String sql = """
            UPDATE Customer
            SET first_name = ?, last_name = ?, email = ?, address = ?, balance = ?, password_hash = ?
            WHERE customer_id = ?
        """;

        try (Connection conn = DataSourceFactory.get().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, c.getFirstName());
            stmt.setString(2, c.getLastName());
            stmt.setString(3, c.getEmail());
            stmt.setString(4, c.getAddress());
            stmt.setBigDecimal(5, c.getBalance());
            stmt.setString(6, c.getPasswordHash());
            stmt.setInt(7, c.getCustomerId());

            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error updating customer", e);
        }
    }

    @Override
    public boolean delete(int id) {
        String sql = "DELETE FROM Customer WHERE customer_id = ?";

        try (Connection conn = DataSourceFactory.get().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id);
            return stmt.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new RuntimeException("Error deleting customer", e);
        }
    }

    // ----------------------
    // Helper: row → object
    // ----------------------
    private Customer mapRow(ResultSet rs) throws SQLException {
        return new Customer(
                rs.getInt("customer_id"),
                rs.getString("first_name"),
                rs.getString("last_name"),
                rs.getString("email"),
                rs.getString("address"),
                rs.getBigDecimal("balance"),
                rs.getString("password_hash")
        );
    }
}
