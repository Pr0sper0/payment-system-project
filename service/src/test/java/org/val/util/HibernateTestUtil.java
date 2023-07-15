package org.val.util;

import lombok.experimental.UtilityClass;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.testcontainers.containers.PostgreSQLContainer;

@UtilityClass
public class HibernateTestUtil {

  private final static PostgreSQLContainer<?> postgres = new PostgreSQLContainer<>("postgres:13");

  static {
    postgres.start();
  }
//
//  public static SessionFactory buildSessionFactory() {
//
//    Configuration cfg = HibernateUtil.buildConfiguration();
//    cfg.setProperty("hibernate.connection.url", postgres.getJdbcUrl());
//    cfg.setProperty("hibernate.connection.username", postgres.getUsername());
//    cfg.setProperty("hibernate.connection.password", postgres.getPassword());
//    cfg.configure();
//
//    return cfg.buildSessionFactory();
//  }
}
