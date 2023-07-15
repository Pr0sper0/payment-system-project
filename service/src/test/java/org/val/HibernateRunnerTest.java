package org.val;

import static org.val.integration.util.TestObjects.ADMIN;
import static org.val.integration.util.TestObjects.IVAN;

import org.junit.jupiter.api.Test;
import org.val.dao.RoleDao;
import org.val.util.HibernateUtil;

public class HibernateRunnerTest {

  @Test
  void initialTest() {
    try (var sessionFactory = HibernateUtil.getSessionFactory();
        var session = sessionFactory.openSession()) {

      var transaction = session.beginTransaction();

      session.save(ADMIN);
      RoleDao roleDao = RoleDao.getInstance(sessionFactory);
      roleDao.findAll();
      transaction.commit();
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}

