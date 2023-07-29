package org.val.dao;

import org.hibernate.SessionFactory;
import org.val.entity.Role;

public class RoleDao extends AbstractDao<Long, Role> {

    private RoleDao(Class<Role> clazz) {
        super(clazz);
    }

    public static RoleDao getInstance() {
        return new RoleDao(Role.class);
    }


}
