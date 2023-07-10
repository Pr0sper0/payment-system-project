package org.val.dao;

import org.hibernate.SessionFactory;
import org.val.entity.User;

public class UserDao extends AbstractDao<Long, User>{


  private UserDao(Class<User> clazz, SessionFactory sessionFactory) {
    super(clazz, sessionFactory);

  }

  public static UserDao getInstance(SessionFactory sessionFactory) {
    return new UserDao(User.class, sessionFactory);
  }
}
