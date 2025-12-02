DROP DATABASE IF EXISTS clothing_store;
CREATE DATABASE IF NOT EXISTS clothing_store;
USE clothing_store;

CREATE TABLE Customer (
	customer_id INT AUTO_INCREMENT PRIMARY KEY,
    first_name VARCHAR(100) NOT NULL,
    last_name VARCHAR(100) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    address VARCHAR(255) NOT NULL,
    balance DECIMAL(10, 2) DEFAULT 0,
    password_hash VARCHAR(100) NOT NULL
);


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

CREATE TABLE Customer_Order (
    order_id INT AUTO_INCREMENT PRIMARY KEY,
    customer_id INT NOT NULL,
    variant_id INT NOT NULL,
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
