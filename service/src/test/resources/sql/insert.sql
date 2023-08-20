-- roles
INSERT INTO roles (role, description, created_at)
VALUES ('Admin', 'Administrator Role', current_timestamp),
       ('User', 'Regular User Role', current_timestamp);

-- users
INSERT INTO users (user_id, name, surname, birthday, email, password, role_id, gender)
VALUES ('user1', 'John', 'Doe', '1990-01-01', 'john@example.com', 'hashed_password_1', 2, 'Male'),
       ('user2', 'Jane', 'Smith', '1985-05-15', 'jane@example.com', 'hashed_password_2', 2,
        'Female');

-- accounts
INSERT INTO accounts (account_id, user_id, balance, currency, created_at)
VALUES ('account1', 'user1', 1000.00, 'USD', current_timestamp),
       ('account2', 'user2', 1500.00, 'EUR', current_timestamp);

-- cards
INSERT INTO cards (card_id, account_id, card_number, cvv, expiration_date, created_at)
VALUES ('card1', 'account1', '1111222233334444', '123', '2025-12-31', current_timestamp),
       ('card2', 'account2', '5555666677778888', '456', '2024-06-30', current_timestamp);

-- products
INSERT INTO products (product_id, name, description, price, created_at)
VALUES ('product1', 'Product A', 'Description of Product A', 49.99, current_timestamp),
       ('product2', 'Product B', 'Description of Product B', 29.99, current_timestamp);

-- orders
INSERT INTO orders (order_id, user_id, product_id, status, created_at)
VALUES ('order1', 'user1', 'product1', 'Pending', current_timestamp),
       ('order2', 'user2', 'product2', 'Shipped', current_timestamp);