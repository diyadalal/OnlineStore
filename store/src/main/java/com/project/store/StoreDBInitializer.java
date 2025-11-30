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

            //creating database tables through JDBC
			stmt.executeUpdate("""
				CREATE TABLE Customer (
					customer_id INT AUTO_INCREMENT PRIMARY KEY,
					first_name VARCHAR(100) NOT NULL,
					last_name VARCHAR(100) NOT NULL,
					email VARCHAR(100) UNIQUE NOT NULL,
					address VARCHAR(255) NOT NULL,
					balance DECIMAL(10, 2) DEFAULT 0,
					password_hash VARCHAR(100) NOT NULL
				);
			""");


            stmt.executeUpdate("""
				CREATE TABLE Product (
					product_id INT AUTO_INCREMENT PRIMARY KEY,
					name varchar(255) NOT NULL,
					category ENUM('Shirt', 'Blouse', 'Sweater', 'Hoodie', 'Jacket', 'Coat', 'Tank Top', 'Pants', 'Shorts', 'Skirt', 'Dress', 'Suit'),
					description TEXT,
					brand varchar(100),
					price DECIMAL(10, 2) NOT NULL,
					sold boolean DEFAULT FALSE,
        
					CHECK (price > 0)
				);
			""");


            stmt.executeUpdate("""
				CREATE TABLE Product_Variant (
					variant_id INT AUTO_INCREMENT PRIMARY KEY,
					product_id INT,
					color_id ENUM('Black', 'White', 'Grey', 'Red', 'Blue', 'Green', 'Yellow', 'Orange', 'Purple', 'Pink'),		
					size_id ENUM('XXS', 'XS', 'S', 'M', 'L', 'XL', 'XXL'),
					stock INT,
    
					CONSTRAINT fk_product_product_variant
						FOREIGN KEY (product_id) REFERENCES Product(product_id)
						ON DELETE CASCADE
						ON UPDATE CASCADE,
    
					CHECK (stock >= 0)
				);
			""");

            stmt.executeUpdate("""
				CREATE TABLE Customer_Order (
					order_id INT AUTO_INCREMENT PRIMARY KEY,
					customer_id INT NOT NULL,
					variant_id INT NOT NULL,
					quantity INT NOT NULL,
					order_date DATETIME DEFAULT CURRENT_TIMESTAMP,

					CONSTRAINT fk_customer_order
						FOREIGN KEY (customer_id) REFERENCES Customer(customer_id)
						ON DELETE CASCADE
						ON UPDATE CASCADE,

					CONSTRAINT fk_variant_order
						FOREIGN KEY (variant_id) REFERENCES Product_Variant(variant_id)
						ON DELETE CASCADE
						ON UPDATE CASCADE
				);
			""");


            stmt.executeUpdate("""
				CREATE TABLE Cart (
					cart_id INT AUTO_INCREMENT PRIMARY KEY,
					customer_id INT NOT NULL,
					variant_id INT NOT NULL,
    
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

			//15 entries per database table through JDBC
			stmt.executeUpdate("""
            INSERT INTO Customer (first_name, last_name, email, address, balance, password_hash) VALUES
                ('John', 'Doe', 'john.doe@gmail.com', '123 Main St', 120.50, '1234'),
                ('Jane', 'Smith', 'jane.smith@yahoo.com', '45 Forest Ave', 705.00, 'abcd'),
                ('Marie', 'Johnson', 'marie.johnson@gmail.com', '223 River Rd', 250.00, 'cats123'),
                ('Bob', 'Williams', 'bob.williams@gmail.com', '27 5th St', 10.00, 'luvr33'),
                ('Priya', 'Singh', 'priya.singh@yahoo.com', '101 St. Carlos St', 45.99, 'xydk33dogs'),
                ('Michael', 'Davis', 'michael.davis@sjsu.edu', '347 Pine Ave', 3000.00, 'psswrd'),
                ('Sarah', 'Miller', 'sarah.miller@sjsu.edu', '22 4th Ave', 5.00, '1a2b3c'),
                ('Emily', 'Wilson', 'emily.wilson@gmail.com', '567 Elm St', 199.95, 'ecomm'),
                ('Laura', 'Moore', 'laura.moore@yahoo.com', '888 Cedar Rd', 22.50, 'cs157a'),
                ('Janice', 'Chang', 'janice.chang@sjsu.edu', '101 Walnut St', 5.00, 'pass123'),
                ('Sophia', 'Anderson', 'sophia.anderson@gmail.com', '77 Aspen Way', 180.00, 'hash11'),
                ('James', 'Thomas', 'james.thomas@yahoo.com', '65 Cherry St', 18.25, 'hash67'),
                ('Olivia', 'Jackson', 'olivia.jackson@gmail.com', '32 Willow Ave', 60.75, 'secret1'),
                ('Henry', 'Creel', 'henry.creel@sjsu.edu', '401 Oak Dr', 500.00, '123pass'),
                ('Grace', 'Harris', 'grace.harris@sjsu.edu', '208 Forest Ln', 12.99, 'passabc');
            """);

			stmt.executeUpdate("""
            INSERT INTO Product (name, category, description, brand, price, sold) VALUES
                ('Everyday T-Shirt', 'Shirt', 'Comfy cotton tee', 'Nike', 9.99, FALSE),
                ('Fuzzy Sweatshirt', 'Hoodie', 'Warm fleece hoodie', 'Adidas', 29.99, FALSE),
                ('Denim Jacket', 'Jacket', 'Blue denim jacket', 'Levis', 39.99, FALSE),
                ('Leather Coat', 'Coat', 'Black leather coat', 'Ralph Lauren', 220.00, FALSE),
                ('Gym Shorts', 'Shorts', 'Breathable running shorts', 'Nike', 19.99, FALSE),
                ('Baggy Jeans', 'Pants', 'Faded blue extra baggy jeans', 'Pacsun', 39.99, FALSE),
                ('Sundress', 'Dress', 'Summer floral mini dress', 'Pacsun', 34.99, FALSE),
                ('Black Tie Suit', 'Suit', 'Formal wear black suit', 'Gucci', 450.00, FALSE),
                ('Tank Top', 'Tank Top', 'Sleeveless basic cotton tank', 'Puma', 12.99, FALSE),
                ('Skater Skirt', 'Skirt', 'High-waisted skirt', 'Journeys', 24.99, FALSE),
                ('Wool Sweater', 'Sweater', 'Knit wool sweater', 'Prada', 159.99, FALSE),
                ('Button-Up Shirt', 'Shirt', 'Casual button shirt', 'Uniqlo', 25.00, FALSE),
                ('Winter Coat', 'Coat', 'Thick fleece lined winter coat', 'American Eagle', 105.00, FALSE),
                ('Athletic Leggings', 'Pants', 'Comfy leggings for sports and the gym', 'Lulu Lemon', 29.99, FALSE),
                ('Striped Blouse', 'Blouse', 'Casual and sleek women’s blouse', 'Victorias', 27.50, FALSE);
            """);

			stmt.executeUpdate("""
            INSERT INTO Product_Variant (product_id, color_id, size_id, stock) VALUES
            	(1, 'Black', 'M', 20),
            	(2, 'Blue', 'L', 15),
            	(3, 'Grey', 'M', 10),
            	(4, 'Black', 'L', 8),
            	(5, 'Red', 'S', 30),
            	(6, 'Blue', 'M', 25),
            	(7, 'Pink', 'M', 12),
           		(8, 'Black', 'XL', 3),
            	(9, 'White', 'S', 10),
            	(10, 'Purple', 'M', 40),
            	(11, 'Green', 'L', 22),
            	(12, 'White', 'M', 19),
            	(13, 'Grey', 'XL', 22),
            	(14, 'Black', 'L', 12),
            	(15,'Yellow', 'S', 30);
            """);

			stmt.executeUpdate("""
            INSERT INTO Customer_Order (customer_id, variant_id, quantity) VALUES
             	(1, 1, 2),
             	(2, 2, 1),
             	(3, 3, 1),
             	(4, 4, 2),
             	(5, 5, 2),
             	(6, 6, 3),
             	(7, 7, 1),
             	(8, 8, 2),
             	(9, 9, 1),
             	(10, 10, 2),
             	(11, 11, 1),
             	(12, 12, 1),
             	(13, 13, 1),
             	(14, 14, 2),
             	(15, 15, 1);
            """);

			stmt.executeUpdate("""
            INSERT INTO Cart (customer_id, variant_id) VALUES
             	(1, 3),
             	(2, 5),
             	(3, 1),
             	(4, 4),
             	(5, 2),
             	(6, 6),
             	(7, 8),
            	(8, 9),
             	(9, 7),
            	(10, 10),
            	(11, 12),
             	(12, 11),
            	(13, 15),
            	(14, 13),
            	(15, 14);
             
            """);

			System.out.println("Database created");


        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        new StoreDBInitializer().initialize();
    }
}
