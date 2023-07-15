package org.val.dao;

import org.hibernate.SessionFactory;
import org.val.entity.Role;

public class RoleDao extends AbstractDao<Long, Role> {

    private RoleDao(Class<Role> clazz, SessionFactory sessionFactory) {
        super(clazz, sessionFactory);
    }

    public static RoleDao getInstance(SessionFactory sessionFactory) {
        return new RoleDao(Role.class, sessionFactory);
    }


}
