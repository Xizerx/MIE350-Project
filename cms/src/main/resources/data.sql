INSERT INTO suppliers (supplier_id, name, email, phone) VALUES(183894, 'ABX', 'abx@gmail.com', '905-246-7777');
INSERT INTO suppliers (supplier_id, name, email, phone) VALUES(385392, 'DUW', 'duw@yahoo.com', '416-234-1988');
INSERT INTO suppliers (supplier_id, name, email, phone) VALUES(295610, 'ABD', 'abd@yahoo.com', '289-407-9011');
INSERT INTO suppliers (supplier_id, name, email, phone) VALUES(194837, 'LRC', 'lrc@gmail.com', '289-390-0222');



INSERT INTO products (product_id, supplier_id, name, category, price, is_bundle) VALUES (172894, 183894, 'ABX', 'nails', 24.99, FALSE);
INSERT INTO products (product_id, supplier_id, name, category, price, is_bundle) VALUES(385392, 385392, 'DUW', 'nails', 25.49, TRUE);
INSERT INTO products (product_id, supplier_id, name, category, price, is_bundle) VALUES(295610, 295610, 'ABX', 'nails', 24.99, FALSE);
INSERT INTO products (product_id, supplier_id, name, category, price, is_bundle) VALUES(194837, 194837, 'LRC', 'nails', 22.00, FALSE);


INSERT INTO inventory (inventory_id, product_id, shape, size, style, color, stock_quantity, reorder_level) VALUES(876, 172894, 'almond', 'large', 'contemporary', 'purple', 20, 10);
INSERT INTO inventory (inventory_id, product_id, shape, size, style, color, stock_quantity, reorder_level) VALUES(248, 385392, 'square', 'medium', 'modern', 'red', 25, 10);
INSERT INTO inventory (inventory_id, product_id, shape, size, style, color, stock_quantity, reorder_level) VALUES(722, 295610, 'round', 'small', 'vintage', 'pink', 25, 10);
INSERT INTO inventory (inventory_id, product_id, shape, size, style, color, stock_quantity, reorder_level) VALUES(409, 194837, 'square', 'small', 'edgy', 'blue', 25, 10);



INSERT INTO orders (order_id, customer_name, customer_email, status, created_at) VALUES(9012, 'Sarah', 'sarah.bell@gmail.com', 'PLACED', '2026-03-4 18:11:26');
INSERT INTO orders (order_id, customer_name, customer_email, status, created_at) VALUES(8014, 'Ryan', 'ryan.smith@yahoo.com', 'PLACED', '2026-03-10 15:12:24');
INSERT INTO orders (order_id, customer_name, customer_email, status, created_at) VALUES(7732, 'Jenna', 'jenna.rogers@gmail.com', 'PLACED', '2026-03-10 12:13:24');
INSERT INTO orders (order_id, customer_name, customer_email, status, created_at) VALUES(1132, 'Rachel', 'rachel.meyers@yahoo.com', 'PLACED', '2026-03-10 10:12:20');


INSERT INTO order_items (order_item_id, order_id, product_id, shape, size, quantity, unit_price) VALUES(78, 9012, 172894, 'almond', 'large', 1, 24.99);
INSERT INTO order_items (order_item_id, order_id, product_id, shape, size, quantity, unit_price) VALUES(54, 8014, 172894, 'almond', 'large', 1, 24.99);
INSERT INTO order_items (order_item_id, order_id, product_id, shape, size, quantity, unit_price) VALUES(12, 7732, 295610, 'round', 'small', 2, 24.99);
INSERT INTO order_items (order_item_id, order_id, product_id, shape, size, quantity, unit_price) VALUES(35, 1132, 194837, 'square', 'small', 2, 22.00);