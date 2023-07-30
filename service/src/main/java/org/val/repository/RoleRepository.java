package org.val.repository;

import org.hibernate.Session;
import org.val.entity.Role;

public class RoleRepository extends AbstractRepository<Long, Role> {

    private RoleRepository(Session session) {
        super(Role.class, session);
    }

    public static RoleRepository getInstance(Session session) {
        return new RoleRepository(session);
    }


}
