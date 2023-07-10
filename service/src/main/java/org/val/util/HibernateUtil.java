package org.val.util;


import java.sql.SQLException;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.experimental.UtilityClass;

import java.sql.DriverManager;
import java.sql.SQLException;
import lombok.experimental.UtilityClass;
import org.hibernate.SessionBuilder;

import org.hibernate.SessionFactory;
import org.hibernate.boot.model.naming.CamelCaseToUnderscoresNamingStrategy;
import org.hibernate.cfg.Configuration;
import org.val.converter.BirthdayConverter;
import org.val.entity.Account;
import org.val.entity.Card;
import org.val.entity.Order;
import org.val.entity.Product;
import org.val.entity.Role;
import org.val.entity.User;

@UtilityClass
public class HibernateUtil {


  private static volatile SessionFactory sessionFactory;

  public static SessionFactory getSessionFactory() {
    if (sessionFactory == null) {
      synchronized (HibernateUtil.class) {
        if (sessionFactory == null) {
          Configuration cfg = buildConfiguration();
          cfg.configure();
          sessionFactory = cfg.buildSessionFactory();
        }
      }
    }
    return sessionFactory;
  }

  public static Configuration buildConfiguration() {
    Configuration cfg = new Configuration();
    cfg.addAnnotatedClass(User.class);
    cfg.addAnnotatedClass(Role.class);
    cfg.addAnnotatedClass(Account.class);
    cfg.addAnnotatedClass(Card.class);
    cfg.addAnnotatedClass(Order.class);
    cfg.addAnnotatedClass(Product.class);
    cfg.setPhysicalNamingStrategy(new CamelCaseToUnderscoresNamingStrategy());
    cfg.addAttributeConverter(new BirthdayConverter());

    return cfg;
  }

}
