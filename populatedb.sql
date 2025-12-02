USE clothing_store;

-----------------------------------------------------------
-- CUSTOMER (20 rows)
-----------------------------------------------------------
INSERT INTO Customer (first_name, last_name, email, address, balance, password_hash) VALUES
('Alice','Rivera','alice1@example.com','123 Maple St',120.50,'hash1'),
('Ben','Turner','ben2@example.com','45 Oak Ave',50.00,'hash2'),
('Cara','Lopez','cara3@example.com','89 Pine Rd',0.00,'hash3'),
('David','Singh','david4@example.com','12 Birch Ln',15.75,'hash4'),
('Ella','Carter','ella5@example.com','65 Cedar Pl',300.90,'hash5'),
('Finn','Hayes','finn6@example.com','77 Elm St',5.00,'hash6'),
('Grace','Kim','grace7@example.com','98 Walnut St',89.99,'hash7'),
('Henry','Brooks','henry8@example.com','14 Aspen Ct',12.40,'hash8'),
('Ivy','Patel','ivy9@example.com','23 Sycamore Dr',0.00,'hash9'),
('Jack','Stewart','jack10@example.com','41 Redwood Rd',200.00,'hash10'),
('Kira','Holt','kira11@example.com','8 Chestnut Way',44.00,'hash11'),
('Liam','Frost','liam12@example.com','30 Poplar St',76.30,'hash12'),
('Maya','Nguyen','maya13@example.com','11 Willow Rd',150.00,'hash13'),
('Noah','Dawson','noah14@example.com','56 Linden Ave',0.00,'hash14'),
('Olive','Reed','olive15@example.com','90 Fir St',63.25,'hash15'),
('Pax','Sullivan','pax16@example.com','22 Spruce Dr',80.00,'hash16'),
('Quinn','Lane','quinn17@example.com','73 Dogwood Ln',19.99,'hash17'),
('Rhea','Park','rhea18@example.com','3 Hemlock Ct',99.50,'hash18'),
('Sam','Benton','sam19@example.com','29 Hickory Pl',0.00,'hash19'),
('Tara','Vega','tara20@example.com','54 Alder Rd',34.70,'hash20');

-----------------------------------------------------------
-- PRODUCT (20 rows)
-----------------------------------------------------------
INSERT INTO Product (name, category, description, brand, price, sold) VALUES
('Cotton Tee','Shirt','Soft cotton tee','AlphaWear',19.99,FALSE),
('Silk Blouse','Blouse','Elegant silk blouse','ModaLux',49.99,FALSE),
('Wool Sweater','Sweater','Warm wool sweater','CozyCo',69.50,FALSE),
('Graphic Hoodie','Hoodie','Printed streetwear hoodie','UrbanFit',59.00,FALSE),
('Denim Jacket','Jacket','Classic denim jacket','BlueStone',89.99,FALSE),
('Winter Coat','Coat','Heavy-duty winter coat','NorthPeak',120.00,FALSE),
('Sport Tank','Tank Top','Breathable sport tank','FitPro',25.00,FALSE),
('Slim Pants','Pants','Slim cut pants','FlexiWear',45.99,FALSE),
('Casual Shorts','Shorts','Light summer shorts','SunLite',29.99,FALSE),
('Pleated Skirt','Skirt','Chic pleated skirt','LunaWear',39.99,FALSE),
('Summer Dress','Dress','Floral summer dress','BloomFashion',55.00,FALSE),
('Business Suit','Suit','Two-piece classic suit','ExecutiveLine',180.00,FALSE),
('Linen Shirt','Shirt','Light linen shirt','BreezeCo',35.00,FALSE),
('Crop Hoodie','Hoodie','Trendy cropped hoodie','GlowUp',42.00,FALSE),
('Trench Coat','Coat','Stylish trench coat','MetroStyle',130.00,FALSE),
('Leather Jacket','Jacket','Genuine leather biker jacket','IronHide',210.00,FALSE),
('Cable Sweater','Sweater','Chunky cable-knit sweater','WarmNest',75.00,FALSE),
('Running Shorts','Shorts','Performance running shorts','AthletiQ',33.00,FALSE),
('Maxi Dress','Dress','Flowy ankle-length dress','SerenityWear',60.00,FALSE),
('Cargo Pants','Pants','Utility cargo pants','TrailCraft',55.50,FALSE);

-----------------------------------------------------------
-- Example Product_Variant entries for multiple products
INSERT INTO Product_Variant (product_id, color_id, size_id, stock) VALUES
-- Variants for Product 1
(1, 'Black', 'S', 10),
(1, 'Black', 'M', 15),
(1, 'White', 'M', 12),
-- Variants for Product 2
(2, 'Red', 'S', 8),
(2, 'Red', 'M', 10),
(2, 'Blue', 'L', 5),
-- Variants for Product 3
(3, 'Grey', 'M', 20),
(3, 'Grey', 'L', 15),
(3, 'Black', 'XL', 7),
-- Variants for Product 4
(4, 'Blue', 'S', 12),
(4, 'Blue', 'M', 14),
(4, 'Green', 'L', 9),
-- Variants for Product 5
(5, 'Pink', 'XS', 5),
(5, 'Pink', 'S', 8),
(5, 'Purple', 'M', 6),
-- Variants for Product 6
(6, 'Black', 'M', 10),
(6, 'White', 'M', 12),
(6, 'Grey', 'L', 8),
-- Variants for Product 7
(7, 'Red', 'S', 7),
(7, 'Blue', 'M', 10),
(7, 'Green', 'L', 5),

(8,'Black','M',6),
(8,'White','S',6),
(8,'Black','L',6),

(9,'Pink','L',3),
(9,'White','M',3),
(9,'Blue','L',3),

(10,'Purple','S',10),
(10,'Black','M',10),
(10,'White','XS',10),

(11,'Orange','S',14),
(12,'Black','XL',2),
(13,'White','XXS',11),
(14,'Blue','XS',6),
(15,'Grey','M',5),
(16,'Black','L',7),
(17,'Red','S',8),
(18,'Green','XXL',3),
(19,'White','M',5),
(20,'Black','L',9);


-----------------------------------------------------------
-- CART (20 rows)
-- Customer 1–20 with Variant 1–20
-----------------------------------------------------------
INSERT INTO Cart (customer_id, variant_id) VALUES
(1, 1),
(1, 2),
(1, 3);

-- Customer 2
INSERT INTO Cart (customer_id, variant_id) VALUES
(2, 4),
(2, 5),
(2, 6);

-- Customer 3
INSERT INTO Cart (customer_id, variant_id) VALUES
(3, 7),
(3, 8),
(3, 9);

-- Customer 4
INSERT INTO Cart (customer_id, variant_id) VALUES
(4, 10),
(4, 11),
(4, 12);

-- Customer 5
INSERT INTO Cart (customer_id, variant_id) VALUES
(5, 13),
(5, 14),
(5, 15);

-- Customer 6
INSERT INTO Cart (customer_id, variant_id) VALUES
(6, 16),
(6, 17),
(6, 18);

-- Customer 7
INSERT INTO Cart (customer_id, variant_id) VALUES
(7, 19),
(7, 20),
(7, 21);

-- Customer 8
INSERT INTO Cart (customer_id, variant_id) VALUES
(8, 22),
(8, 23),
(8, 24);

-- Customer 9
INSERT INTO Cart (customer_id, variant_id) VALUES
(9, 25),
(9, 26),
(9, 27);

-- Customer 10
INSERT INTO Cart (customer_id, variant_id) VALUES
(10, 28),
(10, 29),
(10, 30);

-- Customer 11
INSERT INTO Cart (customer_id, variant_id) VALUES
(11, 5),
(11, 22),
(11, 14);

-- Customer 12
INSERT INTO Cart (customer_id, variant_id) VALUES
(12, 9),
(12, 18),
(12, 30);

-- Customer 13
INSERT INTO Cart (customer_id, variant_id) VALUES
(13, 2),
(13, 27),
(13, 20);

-- Customer 14
INSERT INTO Cart (customer_id, variant_id) VALUES
(14, 7),
(14, 33),
(14, 9);

-- Customer 15
INSERT INTO Cart (customer_id, variant_id) VALUES
(15, 12),
(15, 25),
(15, 29);

-- Customer 16
INSERT INTO Cart (customer_id, variant_id) VALUES
(16, 1),
(16, 19),
(16, 13);

-- Customer 17
INSERT INTO Cart (customer_id, variant_id) VALUES
(17, 4),
(17, 16),
(17, 17);

-- Customer 18
INSERT INTO Cart (customer_id, variant_id) VALUES
(18, 3),
(18, 20),
(18, 29);

-- Customer 19
INSERT INTO Cart (customer_id, variant_id) VALUES
(19, 11),
(19, 36),
(19, 2);

-- Customer 20
INSERT INTO Cart (customer_id, variant_id) VALUES
(20, 6),
(20, 23),
(20, 8);


-----------------------------------------------------------
-- CUSTOMER_ORDER (20 rows)
-- Also using matching IDs for simplicity
-----------------------------------------------------------
INSERT INTO Customer_Order (customer_id, variant_id) VALUES
(1,1),
(2,4),
(3,7),
(4,10),
(5,13),
(6,17),
(7,20),
(8,23),
(9,25),
(10,28),
(11,5),
(12,30),
(13,21),
(14,33),
(15,12),
(16,19),
(17,4),
(18,3),
(19,2),
(20,6);
