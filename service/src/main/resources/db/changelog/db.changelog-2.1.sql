--roles
INSERT INTO roles (role, description, created_at) VALUES ('Admin', 'Administrator Role', NOW());
INSERT INTO roles (role, description, created_at) VALUES ('User', 'Regular User Role', NOW());

--users
INSERT INTO users (user_name, name, surname, birthday, email, password, role_id, gender)
VALUES ('admin', 'Admin', 'User', '1990-01-01', 'admin@example.com', 'hashedpassword', 1, 'Male');
INSERT INTO users (user_name, name, surname, birthday, email, password, role_id, gender)
VALUES ('user1', 'John', 'Doe', '1995-05-15', 'user1@example.com', 'hashedpassword', 2, 'Male');

--accounts
INSERT INTO accounts (account_type, user_id, balance, currency, created_at)
VALUES ('Savings', 1, 1000.00, 'USD', NOW());
INSERT INTO accounts (account_type, user_id, balance, currency, created_at)
VALUES ('Checking', 2, 500.00, 'USD', NOW());

--cards
INSERT INTO cards (card_id, account_id, card_number, cvv, expiration_date, created_at)
VALUES ('12345678', 1, '1234-5678-9012-3456', '123', '2025-12-31', NOW());
INSERT INTO cards (card_id, account_id, card_number, cvv, expiration_date, created_at)
VALUES ('87654321', 2, '5678-1234-9012-3456', '456', '2024-06-30', NOW());

--products
INSERT INTO products (name, description, price, created_at)
VALUES ('Product A', 'Description for Product A', 99.99, NOW());
INSERT INTO products (name, description, price, created_at)
VALUES ('Product B', 'Description for Product B', 49.99, NOW());

--orders
INSERT INTO orders (order_id, user_id, product_id, status, created_at)
VALUES ('order123', 1, 1, 'Pending', NOW());
INSERT INTO orders (order_id, user_id, product_id, status, created_at)
VALUES ('order456', 2, 2, 'Processing', NOW());