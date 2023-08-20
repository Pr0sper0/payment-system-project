package org.val.integration;

import org.junit.jupiter.api.BeforeAll;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.jdbc.Sql;
import org.testcontainers.containers.PostgreSQLContainer;
import org.val.integration.retention.IT;

@IT
@Sql({"classpath:sql/drop.sql",
        "classpath:sql/init.sql",
        "classpath:sql/insert.sql"})
public abstract class IntegrationTestBase {

    private static final PostgreSQLContainer<?> postgresContainer = new PostgreSQLContainer<>(
            "postgres:14.1");

    @BeforeAll
    static void beforeAll() {
        postgresContainer.start();
        // Set the Spring Boot properties for test environment
//        System.setProperty("spring.datasource.url", postgresContainer.getJdbcUrl());
//        System.setProperty("spring.datasource.username", postgresContainer.getUsername());
//        System.setProperty("spring.datasource.password", postgresContainer.getPassword());
    }

    @DynamicPropertySource
    static void postgresProperties(DynamicPropertyRegistry registry) {
        registry.add("spring.datasource.url", postgresContainer::getJdbcUrl);
    }
}

