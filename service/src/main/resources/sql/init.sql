CREATE TABLE payments.roles
(
    id   SERIAL PRIMARY KEY,
    role VARCHAR(32) NOT NULL ,
    description VARCHAR(64) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP DEFAULT NULL
);

CREATE TABLE IF NOT EXISTS payments.users
(
    id BIGSERIAL PRIMARY KEY ,
    user_id VARCHAR(32) NOT NULL UNIQUE,
    name VARCHAR(64),
    surname VARCHAR(64),
    birthday DATE NOT NULL ,
    email VARCHAR(64) NOT NULL UNIQUE ,
    password VARCHAR(64) NOT NULL ,
    role_id SERIAL NOT NULL ,
    gender VARCHAR(16),
    FOREIGN KEY (role_id) REFERENCES payments.roles (id)
    );

CREATE TABLE payments.accounts
(
    id         SERIAL PRIMARY KEY,
    account_id VARCHAR(32)    NOT NULL UNIQUE,
    user_id    VARCHAR(32)    NOT NULL,
    balance    DECIMAL(10, 2) NOT NULL,
    currency   VARCHAR(16)    NOT NULL,
    created_at TIMESTAMP      NOT NULL,
    updated_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES payments.users (user_id)
);


CREATE TABLE payments.cards
(
    id SERIAL PRIMARY KEY,
    card_id VARCHAR(32) NOT NULL UNIQUE,
    account_id VARCHAR(32) NOT NULL,
    card_number VARCHAR(32) NOT NULL UNIQUE,
    cvv VARCHAR(16) NOT NULL,
    expiration_date DATE NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES payments.accounts (account_id)
);

CREATE TABLE payments.products
(
    id SERIAL PRIMARY KEY,
    product_id VARCHAR(32) NOT NULL UNIQUE,
    name VARCHAR(64) NOT NULL,
    description VARCHAR(64) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP
);


CREATE TABLE payments.orders
(
    id SERIAL PRIMARY KEY,
    order_id VARCHAR(32) NOT NULL UNIQUE,
    user_id VARCHAR(32) NOT NULL,
    product_id VARCHAR(32) NOT NULL,
    status VARCHAR(32) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES payments.users (user_id),
    FOREIGN KEY (product_id) REFERENCES payments.products (product_id)
);