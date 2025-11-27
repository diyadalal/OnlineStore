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
                    password_hash VARCHAR(255) NOT NULL,
                    phone VARCHAR(50),
                    balance DECIMAL(10,2) NOT NULL DEFAULT 0.00,
                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP
                );
            """);


            stmt.executeUpdate("""
                CREATE TABLE Category (
                    category_id INT AUTO_INCREMENT PRIMARY KEY,
                    name VARCHAR(100) UNIQUE NOT NULL
                );
            """);

            stmt.executeUpdate("""
                CREATE TABLE Product (
                    product_id INT AUTO_INCREMENT PRIMARY KEY,
                    category_id INT NOT NULL,
                    name VARCHAR(255) NOT NULL,
                    description TEXT,
                    brand VARCHAR(100),
                    price DECIMAL(10,2) NOT NULL CHECK(price > 0),
                    image_url VARCHAR(255),
                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,

                    FOREIGN KEY (category_id)
                        REFERENCES Category(category_id)
                        ON DELETE CASCADE
                        ON UPDATE CASCADE
                );
            """);


            stmt.executeUpdate("""
                CREATE TABLE Product_Variant (
                    variant_id INT AUTO_INCREMENT PRIMARY KEY,
                    product_id INT NOT NULL,
                    size ENUM('XXS','XS','S','M','L','XL','XXL') NOT NULL,
                    color ENUM('Black','White','Grey','Red','Blue','Green','Yellow') NOT NULL,
                    stock INT NOT NULL DEFAULT 0 CHECK(stock >= 0),

                    FOREIGN KEY (product_id)
                        REFERENCES Product(product_id)
                        ON DELETE CASCADE
                        ON UPDATE CASCADE,

                    UNIQUE (product_id, size, color)
                );
            """);

            stmt.executeUpdate("""
                CREATE TABLE Customer_Order (
                    order_id INT AUTO_INCREMENT PRIMARY KEY,
                    customer_id INT NOT NULL,
                    variant_id INT NOT NULL,
                    quantity INT NOT NULL CHECK(quantity > 0),
                    total_price DECIMAL(10,2) NOT NULL CHECK(total_price >= 0),
                    order_status ENUM('Pending','Paid','Shipped','Delivered','Cancelled')
                    DEFAULT 'Pending',
                    created_at DATETIME DEFAULT CURRENT_TIMESTAMP,

                    FOREIGN KEY (customer_id)
                        REFERENCES Customer(customer_id),

                    FOREIGN KEY (variant_id)
                        REFERENCES Product_Variant(variant_id)
                );
            """);


            stmt.executeUpdate("""
                CREATE TABLE Cart (
                    customer_id INT NOT NULL,
                    variant_id INT NOT NULL,
                    quantity INT NOT NULL DEFAULT 1 CHECK(quantity > 0),
                    added_at DATETIME DEFAULT CURRENT_TIMESTAMP,

                    FOREIGN KEY (customer_id)
                        REFERENCES Customer(customer_id)
                        ON DELETE CASCADE
                        ON UPDATE CASCADE,

                    FOREIGN KEY (variant_id)
                        REFERENCES Product_Variant(variant_id)
                        ON DELETE CASCADE
                        ON UPDATE CASCADE,

                    UNIQUE (customer_id, variant_id)
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
