package org.val.integration;

import java.sql.SQLException;
import java.util.List;
import lombok.Cleanup;
import lombok.SneakyThrows;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.val.util.HibernateUtil;

public abstract class IntegrationTestBase {

    private static final String CLEAN_SQL = "DELETE FROM payments.users;"
            + "DELETE FROM payments.accounts;"
            + "DELETE FROM payments.roles;"
            + "DELETE FROM payments.cards;"
            + "DELETE FROM payments.products;"
            + "DELETE FROM payments.orders;";

    private static final String CREATE_SQL = """
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
                  
                  """;
    private static final String INSERT_SQL = """
            """;

    // @BeforeEach
    @SneakyThrows
    void prepareDatabase() {
        Configuration configuration = new Configuration();
        configuration.configure();

        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
                Session session = sessionFactory.openSession()) {
            session.beginTransaction();

            //session.createQuery(CLEAN_SQL).executeUpdate();
//        session.createNamedQuery(CREATE_SQL).executeUpdate();
            //session.createNamedQuery(INSERT_SQL).executeUpdate();

            session.getTransaction().commit();
        }
    }

//    @SneakyThrows
//    @BeforeEach
    @BeforeEach
    void clearDDL() {
//        Configuration configuration = new Configuration();
//        configuration.configure();

        try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
                Session session = sessionFactory.openSession()) {
            session.beginTransaction();
            String hqlRole = "DELETE FROM Role";
            String hqlUser = "DELETE FROM User";
            String hqlAccount = "DELETE FROM Account";

            String hqlCard = "DELETE FROM Card";
            String hqlProduct = "DELETE FROM Product";
            String hqlOrder = "DELETE FROM Order";

            List<String> queries = List.of(hqlUser, hqlOrder, hqlProduct, hqlCard, hqlRole, hqlAccount);

            queries.forEach(query -> {
                session.createQuery(query).executeUpdate();
            });

            session.getTransaction().commit();
        }
    }
}
