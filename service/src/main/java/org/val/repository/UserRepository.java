package org.val.repository;

import org.hibernate.Session;
import org.val.entity.User;

public class UserRepository extends AbstractRepository<Long, User> {

  public UserRepository(Session session) {
    super(User.class, session);
  }
}
