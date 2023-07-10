package org.val.integration;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeEach;

public abstract class IntegrationTestBase {

  private static final String CLEAN_SQL = "DROP TABLE IF EXISTS users;"
      + "DROP TABLE IF EXISTS accounts;"
      + "DROP TABLE IF EXISTS roles;"
      + "DROP TABLE IF EXISTS cards;"
      + "DROP TABLE IF EXISTS products;"
      + "DROP TABLE IF EXISTS orders;";

  private static final String CREATE_SQL = """
            CREATE TABLE IF NOT EXISTS payments.users
            (
                id INT PRIMARY KEY ,
                user_id VARCHAR(32) NOT NULL UNIQUE,
                name VARCHAR(64),
                surname VARCHAR(64),
                birthday DATE NOT NULL ,
                email VARCHAR(64) NOT NULL UNIQUE ,
                password VARCHAR(64) NOT NULL ,
                role VARCHAR(32) NOT NULL ,
                gender VARCHAR(16)
            );
            
            CREATE TABLE payments.accounts
            (
                id         INT PRIMARY KEY,
                account_id VARCHAR(32)    NOT NULL UNIQUE,
                user_id    VARCHAR(32)    NOT NULL,
                balance    DECIMAL(10, 2) NOT NULL,
                currency   VARCHAR(16)    NOT NULL,
                created_at TIMESTAMP      NOT NULL,
                updated_at TIMESTAMP      NOT NULL,
                FOREIGN KEY (user_id) REFERENCES payments.users (user_id)
            );
            
            CREATE TABLE payments.roles
            (
                id   INT PRIMARY KEY,
                user_id VARCHAR(32) NOT NULL UNIQUE,
                role VARCHAR(32) NOT NULL ,
                description VARCHAR(64) NOT NULL,
                created_at TIMESTAMP NOT NULL,
                updated_at TIMESTAMP NOT NULL,
                deleted_at TIMESTAMP,
                FOREIGN KEY (user_id) REFERENCES payments.users (user_id)
            );
            
            CREATE TABLE payments.cards
            (
                id INT PRIMARY KEY,
                card_id VARCHAR(32) NOT NULL UNIQUE,
                account_id VARCHAR(32) NOT NULL,
                card_number VARCHAR(32) NOT NULL UNIQUE,
                cvv VARCHAR(16) NOT NULL,
                expiration_date DATE NOT NULL,
                created_at TIMESTAMP NOT NULL,
                updated_at TIMESTAMP NOT NULL,
                deleted_at TIMESTAMP,
                FOREIGN KEY (account_id) REFERENCES payments.accounts (account_id)
            );
            
            CREATE TABLE payments.products
            (
                id INT PRIMARY KEY,
                product_id VARCHAR(32) NOT NULL UNIQUE,
                name VARCHAR(64) NOT NULL,
                description VARCHAR(64) NOT NULL,
                price DECIMAL(10, 2) NOT NULL,
                created_at TIMESTAMP NOT NULL,
                updated_at TIMESTAMP NOT NULL,
                deleted_at TIMESTAMP
            );
            
            
            CREATE TABLE payments.orders
            (
                id INT PRIMARY KEY,
                order_id VARCHAR(32) NOT NULL UNIQUE,
                user_id VARCHAR(32) NOT NULL,
                product_id VARCHAR(32) NOT NULL,
                status VARCHAR(32) NOT NULL,
                created_at TIMESTAMP NOT NULL,
                updated_at TIMESTAMP NOT NULL,
                deleted_at TIMESTAMP,
                FOREIGN KEY (user_id) REFERENCES payments.users (user_id),
                FOREIGN KEY (product_id) REFERENCES payments.products (product_id)
            );
            
            """;
  private static final String INSERT_SQL = """
      """;

  @BeforeEach
  @SneakyThrows
  void prepareDatabase() {
      Configuration configuration = new Configuration();
      configuration.configure();

      try (SessionFactory sessionFactory = configuration.buildSessionFactory();
          Session session = sessionFactory.openSession()) {
        session.beginTransaction();

        session.createNamedQuery(CLEAN_SQL).executeUpdate();
        session.createNamedQuery(CREATE_SQL).executeUpdate();
        session.createNamedQuery(INSERT_SQL).executeUpdate();

        session.getTransaction().commit();
      }
    }
  }
