package org.val.repository;

import org.hibernate.Session;
import org.val.entity.User;

public class UserRepository extends AbstractRepository<Long, User> {

  private UserRepository(Session session) {
    super(User.class, session);
  }

  public static UserRepository of(Session session) {
    return new UserRepository(session);
  }
}
