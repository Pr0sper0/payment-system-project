package org.val.repository;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import org.hibernate.Session;


public abstract class AbstractRepository<K extends Serializable, T> implements Repository<K, T> {

    protected Session session;

    private final Class<T> clazz;

    protected AbstractRepository(Class<T> clazz, Session session) {
        this.clazz = clazz;
        this.session = session;
    }

    @Override
    public List<T> findAll() {
        var criteria = session.getCriteriaBuilder().createQuery(clazz);
        criteria.select(criteria.from(clazz));
        return session.createQuery(criteria).getResultList();
    }

    @Override
    public Optional<T> findById(K id) {
        T entity = session.find(clazz, id);
        return Optional.ofNullable(entity);
    }

    @Override
    public T save(T entity) {
        session.persist(entity);
        return entity;
    }

    @Override
    public void update(T entity) {
        session.merge(entity);
    }

    @Override
    public boolean delete(K id) {
        T entity = session.find(clazz, id);
        if (entity != null) {
            session.remove(entity);
            session.flush();
            return true;
        }
        return false;
    }
}
