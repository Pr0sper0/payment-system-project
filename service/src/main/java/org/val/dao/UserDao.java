package org.val.dao;


import org.hibernate.SessionFactory;
import org.val.entity.User;

public class UserDao extends AbstractDao<Long, User>{


  private UserDao(Class<User> clazz, SessionFactory sessionFactory) {
    super(clazz, sessionFactory);

  }

  public static UserDao getInstance(SessionFactory sessionFactory) {
    return new UserDao(User.class, sessionFactory);

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.val.entity.User;
import org.val.util.HibernateUtil;

@NoArgsConstructor
public class UserDao implements Dao<Long, User>{

  private Session currentSession;

  private Transaction currentTransaction;

  public Session openCurrentSession() throws SQLException {
    currentSession = HibernateUtil.buildSessionFactory().openSession();
    return currentSession;
  }

  public Session getCurrentSession() {
    return currentSession;
  }

  public void setCurrentSession(Session currentSession) {
    this.currentSession = currentSession;
  }

  public void closeCurrentSession() {
    currentSession.close();
  }


  @Override
  public List<User> findAll() {
    List<User> users = (List<User>) getCurrentSession().createQuery("from User").list();
    return users;
  }

  @Override
  public Optional<User> findById(Long id) {
    User user = (User) getCurrentSession().get(User.class, id);
    return Optional.ofNullable(user);
  }

  @Override
  public boolean delete(Long id) {
    User user = (User) getCurrentSession().get(User.class, id);
    getCurrentSession().delete(user);
    return true;
  }

  @Override
  public void update(User entity) {
    getCurrentSession().update(entity);
  }

  @Override
  public User save(User entity) {
    getCurrentSession().save(entity);
    return entity;

  }
}
