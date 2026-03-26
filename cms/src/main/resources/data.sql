-- SUPPLIERS (4 rows)
INSERT INTO suppliers (supplier_id, name, email, phone) VALUES(183894, 'ABX', 'abx@gmail.com', '905-246-7777');
INSERT INTO suppliers (supplier_id, name, email, phone) VALUES(385392, 'DUW', 'duw@yahoo.com', '416-234-1988');
INSERT INTO suppliers (supplier_id, name, email, phone) VALUES(295610, 'ABD', 'abd@yahoo.com', '289-407-9011');
INSERT INTO suppliers (supplier_id, name, email, phone) VALUES(194837, 'LRC', 'lrc@gmail.com', '289-390-0222');

-- PRODUCTS (10 rows)
INSERT INTO products (product_id, supplier_id, name, description, category, price, size, color, variant, is_bundle, active, imageUrl) VALUES (172894, 183894, 'Midnight Almond', 'Dark purple press-on nails with contemporary design.', 'nails', 24.99, 'large', 'purple', 'contemporary', FALSE, TRUE, 'https://images.pexels.com/photos/7664093/pexels-photo-7664093.jpeg');
INSERT INTO products (product_id, supplier_id, name, description, category, price, size, color, variant, is_bundle, active, imageUrl) VALUES (385392, 385392, 'Crimson Square Kit', 'Red square nails with application kit included.', 'nails', 25.49, 'medium', 'red', 'modern', TRUE, TRUE, 'https://images.pexels.com/photos/7683058/pexels-photo-7683058.jpeg');
INSERT INTO products (product_id, supplier_id, name, description, category, price, size, color, variant, is_bundle, active, imageUrl) VALUES (295610, 295610, 'Blush Round', 'Soft pink round nails perfect for everyday.', 'nails', 24.99, 'small', 'pink', 'vintage', FALSE, TRUE, 'https://images.pexels.com/photos/7470116/pexels-photo-7470116.jpeg');
INSERT INTO products (product_id, supplier_id, name, description, category, price, size, color, variant, is_bundle, active, imageUrl) VALUES (183837, 194837, 'Sapphire Edge', 'Striking blue nails.', 'nails', 22.00, 'small', 'blue', 'edgy', FALSE, TRUE, 'https://images.pexels.com/photos/18192260/pexels-photo-18192260.jpeg');
INSERT INTO products (product_id, supplier_id, name, description, category, price, size, color, variant, is_bundle, active, imageUrl) VALUES (555111, 183894, 'Gold Chain', '18k gold plated chain.', 'necklaces', 45.00, '18in', 'gold', 'modern', FALSE, TRUE, 'https://images.pexels.com/photos/6576196/pexels-photo-6576196.jpeg');
INSERT INTO products (product_id, supplier_id, name, description, category, price, size, color, variant, is_bundle, active, imageUrl) VALUES (555222, 183894, 'Silver Choker', 'Sterling silver minimal choker.', 'necklaces', 35.00, '14in', 'silver', 'edgy', FALSE, TRUE, 'https://images.pexels.com/photos/7514808/pexels-photo-7514808.jpeg');
INSERT INTO products (product_id, supplier_id, name, description, category, price, size, color, variant, is_bundle, active, imageUrl) VALUES (555333, 295610, 'Pearl Drop', 'Vintage style pearl necklace.', 'necklaces', 55.00, '16in', 'white', 'vintage', FALSE, TRUE, 'https://images.pexels.com/photos/36609220/pexels-photo-36609220.jpeg');
INSERT INTO products (product_id, supplier_id, name, description, category, price, size, color, variant, is_bundle, active, imageUrl) VALUES (666111, 385392, 'Aviator Classic', 'Timeless gold aviators.', 'sunglasses', 85.00, 'medium', 'gold', 'classic', FALSE, TRUE, 'https://images.pexels.com/photos/1798665/pexels-photo-1798665.jpeg');
INSERT INTO products (product_id, supplier_id, name, description, category, price, size, color, variant, is_bundle, active, imageUrl) VALUES (666222, 194837, 'Cat Eye Chic', 'Bold black cat eye frames.', 'sunglasses', 65.00, 'small', 'black', 'modern', FALSE, TRUE, 'https://images.pexels.com/photos/6253858/pexels-photo-6253858.jpeg');
INSERT INTO products (product_id, supplier_id, name, description, category, price, size, color, variant, is_bundle, active, imageUrl) VALUES (666333, 194837, 'Oversized Glam', 'Large tortoiseshell sunglasses.', 'sunglasses', 75.00, 'large', 'brown', 'bohemian', FALSE, TRUE, 'https://images.pexels.com/photos/29538708/pexels-photo-29538708.jpeg');
INSERT INTO products (product_id, supplier_id, name, description, category, price, size, color, variant, is_bundle, active, imageUrl) VALUES (777111, 385392, 'Nude Coffin', 'Beige coffin-shaped press-on nails.', 'nails', 23.99, 'medium', 'beige', 'modern', FALSE, TRUE, 'https://images.pexels.com/photos/36207029/pexels-photo-36207029.jpeg');
INSERT INTO products (product_id, supplier_id, name, description, category, price, size, color, variant, is_bundle, active, imageUrl) VALUES (777222, 183894, 'Glitter Galaxy', 'Holographic glitter press-on nails.', 'nails', 26.99, 'large', 'pink', 'contemporary', FALSE, TRUE, 'https://images.pexels.com/photos/939836/pexels-photo-939836.jpeg');
INSERT INTO products (product_id, supplier_id, name, description, category, price, size, color, variant, is_bundle, active, imageUrl) VALUES (777333, 295610, 'French Tip Kit', 'Classic white French tip nails with application kit.', 'nails', 27.49, 'small', 'white', 'classic', TRUE, TRUE, 'https://images.pexels.com/photos/4374426/pexels-photo-4374426.jpeg');
INSERT INTO products (product_id, supplier_id, name, description, category, price, size, color, variant, is_bundle, active, imageUrl) VALUES (888111, 194837, 'Gold Pendant', 'Gold heart pendant necklace.', 'necklaces', 48.00, '20in', 'gold', 'romantic', FALSE, TRUE,'https://images.pexels.com/photos/5441011/pexels-photo-5441011.jpeg');
INSERT INTO products (product_id, supplier_id, name, description, category, price, size, color, variant, is_bundle, active, imageUrl) VALUES (888222, 183894, 'Layered Chain Set', 'Multi-layer gold chain bundle set.', 'necklaces', 65.00, '16in', 'gold', 'bohemian', TRUE, TRUE, 'https://images.pexels.com/photos/6604737/pexels-photo-6604737.jpeg');
INSERT INTO products (product_id, supplier_id, name, description, category, price, size, color, variant, is_bundle, active, imageUrl) VALUES (999111, 295610, 'Round Retro', 'Round tortoiseshell retro frames.', 'sunglasses', 70.00, 'medium', 'brown', 'vintage', FALSE, TRUE, 'https://images.pexels.com/photos/6904525/pexels-photo-6904525.jpeg');
INSERT INTO products (product_id, supplier_id, name, description, category, price, size, color, variant, is_bundle, active, imageUrl) VALUES (999222, 385392, 'Shield Sport', 'Mirrored sport shield sunglasses.', 'sunglasses', 90.00, 'large', 'black', 'edgy', FALSE, TRUE, 'https://images.pexels.com/photos/7666401/pexels-photo-7666401.jpeg');

-- INVENTORY (17 rows)
INSERT INTO inventory (inventory_id, product_id, stock_quantity, reorder_level, reorderQuantity, warehouseLocation) VALUES (876, 172894, 20, 10, 50, 'Zone-A1');
INSERT INTO inventory (inventory_id, product_id, stock_quantity, reorder_level, reorderQuantity, warehouseLocation) VALUES (248, 385392, 25, 10, 50, 'Zone-B2');
INSERT INTO inventory (inventory_id, product_id, stock_quantity, reorder_level, reorderQuantity, warehouseLocation) VALUES (722, 295610, 25, 10, 50, 'Zone-A2');
INSERT INTO inventory (inventory_id, product_id, stock_quantity, reorder_level, reorderQuantity, warehouseLocation) VALUES (409, 183837, 25, 10, 50, 'Zone-C1');
INSERT INTO inventory (inventory_id, product_id, stock_quantity, reorder_level, reorderQuantity, warehouseLocation) VALUES (410, 555111, 15, 5, 20, 'Zone-D1');
INSERT INTO inventory (inventory_id, product_id, stock_quantity, reorder_level, reorderQuantity, warehouseLocation) VALUES (411, 555222, 5, 10, 20, 'Zone-D2'); -- Low stock!
INSERT INTO inventory (inventory_id, product_id, stock_quantity, reorder_level, reorderQuantity, warehouseLocation) VALUES (412, 555333, 30, 5, 20, 'Zone-D3');
INSERT INTO inventory (inventory_id, product_id, stock_quantity, reorder_level, reorderQuantity, warehouseLocation) VALUES (413, 666111, 12, 5, 15, 'Zone-E1');
INSERT INTO inventory (inventory_id, product_id, stock_quantity, reorder_level, reorderQuantity, warehouseLocation) VALUES (414, 666222, 8, 10, 15, 'Zone-E2'); -- Low stock!
INSERT INTO inventory (inventory_id, product_id, stock_quantity, reorder_level, reorderQuantity, warehouseLocation) VALUES (415, 666333, 22, 5, 15, 'Zone-E3');
INSERT INTO inventory (inventory_id, product_id, stock_quantity, reorder_level, reorderQuantity, warehouseLocation) VALUES (416, 777111, 18, 8, 40, 'Zone-B3');
INSERT INTO inventory (inventory_id, product_id, stock_quantity, reorder_level, reorderQuantity, warehouseLocation) VALUES (417, 777222, 3, 10, 40, 'Zone-A4'); -- Low stock!
INSERT INTO inventory (inventory_id, product_id, stock_quantity, reorder_level, reorderQuantity, warehouseLocation) VALUES (418, 777333, 40, 10, 50, 'Zone-B1');
INSERT INTO inventory (inventory_id, product_id, stock_quantity, reorder_level, reorderQuantity, warehouseLocation) VALUES (419, 888111, 7, 8, 25, 'Zone-D4'); -- Low stock!
INSERT INTO inventory (inventory_id, product_id, stock_quantity, reorder_level, reorderQuantity, warehouseLocation) VALUES (420, 888222, 20, 5, 25, 'Zone-D5');
INSERT INTO inventory (inventory_id, product_id, stock_quantity, reorder_level, reorderQuantity, warehouseLocation) VALUES (421, 999111, 6, 8, 20, 'Zone-E4'); -- Low stock!
INSERT INTO inventory (inventory_id, product_id, stock_quantity, reorder_level, reorderQuantity, warehouseLocation) VALUES (422, 999222, 14, 5, 20, 'Zone-E5');

-- CUSTOMERS (8 rows)
INSERT INTO customers (id, firstName, lastName, email, phone, city, preferredNailSize, preferredNecklaceLength, preferredSunglassesSize, preferredStyle, active) VALUES (1, 'Sarah', 'Bell', 'sarah.bell@gmail.com', '416-555-0101', 'Toronto', 'large', '18in', 'medium', 'contemporary', TRUE);
INSERT INTO customers (id, firstName, lastName, email, phone, city, preferredNailSize, preferredNecklaceLength, preferredSunglassesSize, preferredStyle, active) VALUES (2, 'Ryan', 'Smith', 'ryan.smith@yahoo.com', '416-555-0202', 'Toronto', 'medium', '16in', 'large', 'modern', TRUE);
INSERT INTO customers (id, firstName, lastName, email, phone, city, preferredNailSize, preferredNecklaceLength, preferredSunglassesSize, preferredStyle, active) VALUES (3, 'Jenna', 'Rogers', 'jenna.rogers@gmail.com', '905-555-0303', 'Mississauga', 'small', '14in', 'small', 'vintage', TRUE);
INSERT INTO customers (id, firstName, lastName, email, phone, city, preferredNailSize, preferredNecklaceLength, preferredSunglassesSize, preferredStyle, active) VALUES (4, 'Marcus', 'Chen', 'marcus.chen@gmail.com', '416-555-0404', 'Markham', 'medium', '20in', 'medium', 'classic', TRUE);
INSERT INTO customers (id, firstName, lastName, email, phone, city, preferredNailSize, preferredNecklaceLength, preferredSunglassesSize, preferredStyle, active) VALUES (5, 'Olivia', 'Diaz', 'olivia.d@yahoo.com', '647-555-0505', 'Toronto', 'small', '18in', 'small', 'bohemian', TRUE);
INSERT INTO customers (id, firstName, lastName, email, phone, city, preferredNailSize, preferredNecklaceLength, preferredSunglassesSize, preferredStyle, active) VALUES (6, 'David', 'Kim', 'david.k@gmail.com', '905-555-0606', 'Richmond Hill', 'large', '16in', 'large', 'modern', TRUE);
INSERT INTO customers (id, firstName, lastName, email, phone, city, preferredNailSize, preferredNecklaceLength, preferredSunglassesSize, preferredStyle, active) VALUES (7, 'Emma', 'Wilson', 'emma.w@hotmail.com', '416-555-0707', 'Toronto', 'medium', '14in', 'medium', 'vintage', TRUE);
INSERT INTO customers (id, firstName, lastName, email, phone, city, preferredNailSize, preferredNecklaceLength, preferredSunglassesSize, preferredStyle, active) VALUES (8, 'Liam', 'Patel', 'liam.p@gmail.com', '647-555-0808', 'Brampton', 'large', '20in', 'small', 'edgy', TRUE);

-- ORDERS (10 rows)
INSERT INTO orders (order_id, customer_id, orderNumber, status, subtotal, tax, shippingCost, totalAmount, createdAt) VALUES(9012, 1, 'ORD-2026030401', 'DELIVERED', 24.99, 3.25, 5.00, 33.24, '2026-03-04 18:11:26');
INSERT INTO orders (order_id, customer_id, orderNumber, status, subtotal, tax, shippingCost, totalAmount, createdAt) VALUES(8014, 2, 'ORD-2026031002', 'SHIPPED', 69.99, 9.10, 0.00, 79.09, '2026-03-10 15:12:24');
INSERT INTO orders (order_id, customer_id, orderNumber, status, subtotal, tax, shippingCost, totalAmount, createdAt) VALUES(7732, 3, 'ORD-2026031003', 'DELIVERED', 49.98, 6.50, 0.00, 56.48, '2026-03-10 12:13:24');
INSERT INTO orders (order_id, customer_id, orderNumber, status, subtotal, tax, shippingCost, totalAmount, createdAt) VALUES(1132, 1, 'ORD-2026031004', 'PLACED', 44.00, 5.72, 9.99, 59.71, '2026-03-10 10:12:20');
INSERT INTO orders (order_id, customer_id, orderNumber, status, subtotal, tax, shippingCost, totalAmount, createdAt) VALUES(1133, 4, 'ORD-2026031201', 'PROCESSING', 85.00, 11.05, 0.00, 96.05, '2026-03-12 09:30:00');
INSERT INTO orders (order_id, customer_id, orderNumber, status, subtotal, tax, shippingCost, totalAmount, createdAt) VALUES(1134, 5, 'ORD-2026031501', 'SHIPPED', 100.00, 13.00, 0.00, 113.00, '2026-03-15 14:20:00');
INSERT INTO orders (order_id, customer_id, orderNumber, status, subtotal, tax, shippingCost, totalAmount, createdAt) VALUES(1135, 6, 'ORD-2026031801', 'PLACED', 25.49, 3.31, 5.00, 33.80, '2026-03-18 11:45:00');
INSERT INTO orders (order_id, customer_id, orderNumber, status, subtotal, tax, shippingCost, totalAmount, createdAt) VALUES(1136, 7, 'ORD-2026032001', 'DELIVERED', 55.00, 7.15, 0.00, 62.15, '2026-03-20 16:10:00');
INSERT INTO orders (order_id, customer_id, orderNumber, status, subtotal, tax, shippingCost, totalAmount, createdAt) VALUES(1137, 8, 'ORD-2026032101', 'PENDING', 75.00, 9.75, 5.00, 89.75, '2026-03-21 10:05:00');
INSERT INTO orders (order_id, customer_id, orderNumber, status, subtotal, tax, shippingCost, totalAmount, createdAt) VALUES(1138, 2, 'ORD-2026032201', 'PLACED', 35.00, 4.55, 5.00, 44.55, '2026-03-22 13:30:00');

-- ORDER ITEMS (15 rows)
INSERT INTO order_items (order_item_id, order_id, product_id, quantity, unit_price, includesApplicationKit) VALUES(78, 9012, 172894, 1, 24.99, FALSE);
INSERT INTO order_items (order_item_id, order_id, product_id, quantity, unit_price, includesApplicationKit) VALUES(54, 8014, 172894, 1, 24.99, FALSE);
INSERT INTO order_items (order_item_id, order_id, product_id, quantity, unit_price, includesApplicationKit) VALUES(55, 8014, 555111, 1, 45.00, FALSE);
INSERT INTO order_items (order_item_id, order_id, product_id, quantity, unit_price, includesApplicationKit) VALUES(12, 7732, 295610, 2, 24.99, TRUE);
INSERT INTO order_items (order_item_id, order_id, product_id, quantity, unit_price, includesApplicationKit) VALUES(35, 1132, 183837, 2, 22.00, FALSE);
INSERT INTO order_items (order_item_id, order_id, product_id, quantity, unit_price, includesApplicationKit) VALUES(36, 1133, 666111, 1, 85.00, FALSE);
INSERT INTO order_items (order_item_id, order_id, product_id, quantity, unit_price, includesApplicationKit) VALUES(37, 1134, 555222, 1, 35.00, FALSE);
INSERT INTO order_items (order_item_id, order_id, product_id, quantity, unit_price, includesApplicationKit) VALUES(38, 1134, 666222, 1, 65.00, FALSE);
INSERT INTO order_items (order_item_id, order_id, product_id, quantity, unit_price, includesApplicationKit) VALUES(39, 1135, 385392, 1, 25.49, TRUE);
INSERT INTO order_items (order_item_id, order_id, product_id, quantity, unit_price, includesApplicationKit) VALUES(40, 1136, 555333, 1, 55.00, FALSE);
INSERT INTO order_items (order_item_id, order_id, product_id, quantity, unit_price, includesApplicationKit) VALUES(41, 1137, 666333, 1, 75.00, FALSE);
INSERT INTO order_items (order_item_id, order_id, product_id, quantity, unit_price, includesApplicationKit) VALUES(42, 1138, 555222, 1, 35.00, FALSE);
INSERT INTO order_items (order_item_id, order_id, product_id, quantity, unit_price, includesApplicationKit) VALUES(43, 9012, 295610, 1, 24.99, FALSE);
INSERT INTO order_items (order_item_id, order_id, product_id, quantity, unit_price, includesApplicationKit) VALUES(44, 7732, 183837, 1, 22.00, FALSE);
INSERT INTO order_items (order_item_id, order_id, product_id, quantity, unit_price, includesApplicationKit) VALUES(45, 1136, 172894, 1, 24.99, FALSE);