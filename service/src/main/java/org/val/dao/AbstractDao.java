package org.val.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.val.util.HibernateUtil;


public abstract class AbstractDao<K extends Serializable, T> implements Dao<K, T> {

    protected SessionFactory sessionFactory;

    private final Class<T> clazz;

    protected AbstractDao(Class<T> clazz) {
        this.clazz = clazz;
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    @Override
    public List<T> findAll() {
        Session session = sessionFactory.openSession();
        List<T> entities = session.createQuery("from " + clazz.getName(), clazz).list();
        session.close();
        return entities;
    }

    @Override
    public Optional<T> findById(K id) {
        Session session = sessionFactory.openSession();
        T entity = session.get(clazz, id);
        session.close();
        return Optional.ofNullable(entity);
    }

    @Override
    public T save(T entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        return entity;
    }

    @Override
    public void update(T entity) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(entity);
        session.getTransaction().commit();
        session.close();
    }

    @Override
    public boolean delete(K id) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        T entity = session.get(clazz, id);
        if (entity != null) {
            session.delete(entity);
            session.getTransaction().commit();
            session.close();
            return true;
        }
        return false;
    }
}
