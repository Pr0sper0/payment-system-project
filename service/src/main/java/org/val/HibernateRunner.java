package org.val;


import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.val.entity.Gender;
import org.val.entity.Role;
import org.val.entity.User;
import org.val.util.HibernateUtil;

public class HibernateRunner {

  private static final Logger log = LoggerFactory.getLogger(HibernateRunner.class);

  public static void main(String[] args) {

    User user = User.builder()
        .name("Valerii")
        .surname("Tarasov")
        .email("mail@mail.com")
        .gender(Gender.MAN)
        .build();

    Role role = Role.builder()
        .id(1)
        .role("ADMIN")
        .description("Administrator")
        .build();

    try (SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession()) {
      var transaction = session.beginTransaction();
      session.save(role);
      transaction.commit();
    }
  }

}
