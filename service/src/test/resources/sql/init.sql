--liquibase formatted sql

--changeset tarasov:1
CREATE TABLE roles
(
    id   SERIAL PRIMARY KEY,
    role VARCHAR(32) NOT NULL ,
    description VARCHAR(64) NOT NULL,
    created_at TIMESTAMP,
    updated_at TIMESTAMP DEFAULT NULL
);
--rollback DROP TABLE roles;

--changeset tarasov:2
CREATE TABLE IF NOT EXISTS users
(
    id BIGSERIAL PRIMARY KEY ,
    user_name VARCHAR(32) NOT NULL UNIQUE,
    name VARCHAR(64),
    surname VARCHAR(64),
    birthday Date NOT NULL ,
    email VARCHAR(64) NOT NULL UNIQUE ,
    password VARCHAR(64) NOT NULL ,
    role_id SERIAL NOT NULL ,
    gender VARCHAR(16),
    FOREIGN KEY (role_id) REFERENCES roles (id)
    );
--rollback DROP TABLE users;

--changeset tarasov:3
CREATE TABLE accounts
(
    id         SERIAL PRIMARY KEY,
    account_type VARCHAR(32)   NOT NULL UNIQUE,
    user_id    BIGINT    NOT NULL,
    balance    DECIMAL(10, 2) NOT NULL,
    currency   VARCHAR(16)    NOT NULL,
    created_at TIMESTAMP      NOT NULL,
    updated_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id)
);
--rollback DROP TABLE accounts;

--changeset tarasov:4
CREATE TABLE cards
(
    id SERIAL PRIMARY KEY,
    card_id VARCHAR(32) NOT NULL UNIQUE,
    account_id INTEGER NOT NULL,
    card_number VARCHAR(32) NOT NULL UNIQUE,
    cvv VARCHAR(16) NOT NULL,
    expiration_date DATE NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,
    FOREIGN KEY (account_id) REFERENCES accounts (id)
);
--rollback DROP TABLE cards;

--changeset tarasov:5
CREATE TABLE products
(
    id SERIAL PRIMARY KEY,
    name VARCHAR(64) NOT NULL,
    description VARCHAR(64) NOT NULL,
    price DECIMAL(10, 2) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP
);
--rollback DROP TABLE products;

--changeset tarasov:6
CREATE TABLE orders
(
    id SERIAL PRIMARY KEY,
    order_id VARCHAR(32) NOT NULL UNIQUE,
    user_id BIGINT NOT NULL,
    product_id INTEGER NOT NULL,
    status VARCHAR(32) NOT NULL,
    created_at TIMESTAMP NOT NULL,
    updated_at TIMESTAMP,
    deleted_at TIMESTAMP,
    FOREIGN KEY (user_id) REFERENCES users (id),
    FOREIGN KEY (product_id) REFERENCES products (id)
);
--rollback DROP TABLE orders;