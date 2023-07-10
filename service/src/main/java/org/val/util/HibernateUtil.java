package org.val.util;

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

  public static SessionFactory buildSessionFactory() throws SQLException {
    DriverManager.getConnection("db.url", "db.username", "db.password");

    Configuration cfg = buildConfiguration();
    cfg.configure();
    return cfg.buildSessionFactory();
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
//    cfg.registerTypeOverride(new JsonBinaryType());

    return cfg;
  }
}
