package org.val.integration;

import java.sql.SQLException;
import java.util.List;
import lombok.Cleanup;
import lombok.SneakyThrows;
import net.bytebuddy.utility.dispatcher.JavaDispatcher.Container;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testcontainers.containers.PostgreSQLContainer;
import org.val.config.AppConfig;
import org.val.util.HibernateUtil;

@SpringBootTest
public abstract class IntegrationTestBase {

    static AnnotationConfigApplicationContext context;

    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>("postgres:latest")
            .withDatabaseName("test")
            .withUsername("testuser")
            .withPassword("testpassword");

    @BeforeAll
    static void beforeAll() {
        postgresContainer.start();
        // Set the Spring Boot properties for test environment
        System.setProperty("spring.datasource.url", postgresContainer.getJdbcUrl());
        System.setProperty("spring.datasource.username", "testuser");
        System.setProperty("spring.datasource.password", "testpassword");

        context = new AnnotationConfigApplicationContext();
        context.getEnvironment().setActiveProfiles("test");
        context.register(AppConfig.class);
    }

    @AfterAll
    static void afterAll() {
        postgresContainer.stop();
    }
}

