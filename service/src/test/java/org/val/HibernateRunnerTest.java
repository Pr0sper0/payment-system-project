package org.val;

import static org.val.integration.util.TestObjects.ADMIN;
import static org.val.integration.util.TestObjects.IVAN;

import org.junit.jupiter.api.Test;
import org.val.entity.User;
import org.val.util.HibernateUtil;

public class HibernateRunnerTest {

  @Test
  void initialTest() {
    try (var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession()) {

      var transaction = session.beginTransaction();

      session.save(ADMIN);

      session.save(IVAN);
      session.clear();

      session.get(User.class, 1L);
      transaction.commit();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

