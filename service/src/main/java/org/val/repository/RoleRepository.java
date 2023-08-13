package org.val.repository;

import org.hibernate.Session;
import org.val.entity.Role;

public class RoleRepository extends AbstractRepository<Long, Role> {

    public RoleRepository(Session session) {
        super(Role.class, session);
    }
}
