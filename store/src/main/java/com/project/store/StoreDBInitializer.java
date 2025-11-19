package com.project.store;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class StoreDBInitializer {
    public void initialize() {
        String url = "jdbc:mysql://localhost:3306/";
        String user = "root";
        String password = "password"; //change to your SQL username + password when running

        try (Connection conn = DriverManager.getConnection(url, user, password);
             Statement stmt = conn.createStatement()) {

            stmt.executeUpdate("CREATE DATABASE IF NOT EXISTS clothing_store");
            stmt.executeUpdate("USE clothing_store");

            stmt.executeUpdate("""
                CREATE TABLE Customer (
                    customer_id INT AUTO_INCREMENT PRIMARY KEY,
                    first_name VARCHAR(100) NOT NULL,
                    last_name VARCHAR(100) NOT NULL,
                    email VARCHAR(100) UNIQUE NOT NULL,
                    password_hash VARCHAR(100),
                    phone VARCHAR(50),
                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
                );
            """);

            stmt.executeUpdate("""
                CREATE TABLE Address (
                    address_id INT AUTO_INCREMENT PRIMARY KEY,
                    customer_id INT,
                    street VARCHAR(100),
                    city VARCHAR(100),
                    state VARCHAR(100),
                    zip_code VARCHAR(100),
                    is_default BOOLEAN DEFAULT TRUE,
                    CONSTRAINT fk_customer_address
                        FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
                        ON DELETE CASCADE
                        ON UPDATE CASCADE
                );
            """);

            stmt.executeUpdate("""
                CREATE TABLE Category (
                    category_id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(255) UNIQUE NOT NULL,
                    description TEXT
                );
            """);

            stmt.executeUpdate("""
                CREATE TABLE Product (
                    product_id INT AUTO_INCREMENT PRIMARY KEY,
                    category_id INT,
                    name VARCHAR(255) NOT NULL,
                    description TEXT,
                    brand VARCHAR(100),
                    price DECIMAL(10, 2) NOT NULL,
                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                    sold BOOLEAN DEFAULT FALSE,
                    CONSTRAINT fk_product_category
                        FOREIGN KEY (category_id) REFERENCES Category(category_id)
                        ON DELETE CASCADE
                        ON UPDATE CASCADE,
                    CHECK (price > 0)
                );
            """);

            stmt.executeUpdate("""
                CREATE TABLE Size (
                    size_id INT AUTO_INCREMENT PRIMARY KEY,
                    size_label VARCHAR(10) UNIQUE NOT NULL
                );
            """);

            stmt.executeUpdate("""
                CREATE TABLE Color (
                    color_id INT AUTO_INCREMENT PRIMARY KEY,
                    color_label VARCHAR(10) UNIQUE NOT NULL
                );
            """);

            stmt.executeUpdate("""
                CREATE TABLE Product_Variant (
                    variant_id INT AUTO_INCREMENT PRIMARY KEY,
                    product_id INT,
                    size_id INT,
                    color_id INT,
                    stock INT,
                    CONSTRAINT fk_product_product_variant
                        FOREIGN KEY (product_id) REFERENCES Product(product_id)
                        ON DELETE CASCADE
                        ON UPDATE CASCADE,
                    FOREIGN KEY (size_id) REFERENCES Size(size_id),
                    FOREIGN KEY (color_id) REFERENCES Color(color_id),
                    CHECK (stock >= 0)
                );
            """);

            stmt.executeUpdate("""
                CREATE TABLE Customer_Order (
                    order_id INT AUTO_INCREMENT PRIMARY KEY,
                    customer_id INT NOT NULL,
                    address_id INT NOT NULL,
                    total_price DECIMAL(10, 2) NOT NULL,
                    order_status ENUM('Pending', 'Paid', 'Shipped', 'Delivered', 'Cancelled'),
                    payment_method ENUM('Credit Card', 'Debit', 'PayPal', 'Apple Pay', 'Other'),
                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                    CONSTRAINT fk_customer_order
                        FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
                        ON DELETE CASCADE
                        ON UPDATE CASCADE,
                    CONSTRAINT fk_address_order
                        FOREIGN KEY (address_id) REFERENCES Address(address_id)
                        ON DELETE CASCADE
                        ON UPDATE CASCADE
                );
            """);

            stmt.executeUpdate("""
                CREATE TABLE Order_Item (
                    item_id INT AUTO_INCREMENT PRIMARY KEY,
                    order_id INT NOT NULL,
                    variant_id INT NOT NULL,
                    quantity INT NOT NULL,
                    CONSTRAINT fk_order_order_item
                        FOREIGN KEY (order_id) REFERENCES Customer_Order(order_id)
                        ON DELETE CASCADE
                        ON UPDATE CASCADE,
                    CONSTRAINT fk_variant_order_item
                        FOREIGN KEY (variant_id) REFERENCES Product_Variant(variant_id)
                        ON DELETE CASCADE
                        ON UPDATE CASCADE,
                    CHECK (quantity > 0)
                );
            """);

            stmt.executeUpdate("""
                CREATE TABLE Payment (
                    payment_id INT AUTO_INCREMENT PRIMARY KEY,
                    order_id INT NOT NULL,
                    total DECIMAL(10, 2),
                    status ENUM('Pending', 'Completed', 'Failed', 'Refunded') DEFAULT 'Pending',
                    payment_date DATETIME DEFAULT CURRENT_TIMESTAMP,
                    CONSTRAINT fk_order_payment
                        FOREIGN KEY (order_id) REFERENCES Customer_Order(order_id)
                        ON DELETE CASCADE
                        ON UPDATE CASCADE,
                    CHECK (total > 0)
                );
            """);

            stmt.executeUpdate("""
                CREATE TABLE Cart (
                    customer_id INT NOT NULL,
                    variant_id INT NOT NULL,
                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                    CONSTRAINT fk_customer_cart
                        FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
                        ON DELETE CASCADE
                        ON UPDATE CASCADE,
                    CONSTRAINT fk_variant_cart
                        FOREIGN KEY (variant_id) REFERENCES Product_Variant(variant_id)
                        ON DELETE CASCADE
                        ON UPDATE CASCADE
                );
            """);

            stmt.executeUpdate("""
                CREATE TABLE Review (
                    review_id INT AUTO_INCREMENT PRIMARY KEY,
                    customer_id INT NOT NULL,
                    variant_id INT NOT NULL,
                    rating INT NOT NULL,
                    description TEXT,
                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                    CONSTRAINT fk_customer_review
                        FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
                        ON DELETE CASCADE
                        ON UPDATE CASCADE,
                    CONSTRAINT fk_variant_review
                        FOREIGN KEY (variant_id) REFERENCES Product_Variant(variant_id)
                        ON DELETE CASCADE
                        ON UPDATE CASCADE,
                    CHECK (rating BETWEEN 1 AND 5)
                );
            """);

            stmt.executeUpdate("""
                CREATE TABLE Inventory_Log (
                    log_id INT AUTO_INCREMENT PRIMARY KEY,
                    variant_id INT NOT NULL,
                    change_qty INT NOT NULL,
                    reason ENUM('Sold', 'Restock', 'Return'),
                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,
                    CONSTRAINT fk_variant_inventory_log
                        FOREIGN KEY (variant_id) REFERENCES Product_Variant(variant_id)
                        ON DELETE CASCADE
                        ON UPDATE CASCADE
                );
            """);

            System.out.println("Database created");

          //to-do: populate the tables

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new StoreDBInitializer().initialize();
    }
}
