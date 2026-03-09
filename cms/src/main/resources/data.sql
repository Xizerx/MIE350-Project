
INSERT INTO suppliers (supplier_id, name, email, phone) VALUES(1, 'A', 'Person1@email', '1234');
INSERT INTO suppliers (supplier_id, name, email, phone) VALUES(2, 'B', 'Person2@email', '12345');


INSERT INTO products (product_id, supplier_id, name, category, price, is_bundle) VALUES (1, 1, 'A', 'a', 1.2, TRUE);
INSERT INTO products (product_id, supplier_id, name, category, price, is_bundle) VALUES(2, 2, 'B', 'b', 1.3, FALSE);


INSERT INTO inventory (inventory_id, product_id, shape, size, style, color, stock_quantity, reorder_level) VALUES(2, 2, 'A', 'a', 'contemporary', 'purple', 20, 10);
INSERT INTO inventory (inventory_id, product_id, shape, size, style, color, stock_quantity, reorder_level) VALUES(1, 1, 'B', 'b', 'modern', 'red', 25, 10);


INSERT INTO orders (order_id, customer_name, customer_email, status, created_at) VALUES(1, 'Trinity', 'Trinity@email', 'PLACED', '2021-10-19 18:11:24');
INSERT INTO orders (order_id, customer_name, customer_email, status, created_at) VALUES(2, 'Giovanna', 'Giovanna@email', 'PLACED', '2021-10-19 18:11:24');


INSERT INTO order_items (order_item_id, order_id, product_id, shape, size, quantity, unit_price) VALUES(1, 1, 1, 'A', 'a', 20, 1.3);
INSERT INTO order_items (order_item_id, order_id, product_id, shape, size, quantity, unit_price) VALUES(2, 2, 2, 'B', 'b', 25, 1.4);
