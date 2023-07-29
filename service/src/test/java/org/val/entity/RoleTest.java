package org.val.entity;

import static org.assertj.core.api.Assertions.assertThat;
import static org.val.integration.util.TestObjects.ADMIN;
import static org.val.util.HibernateUtil.buildConfiguration;

import java.util.ArrayList;
import java.util.List;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.val.util.HibernateUtil;

class RoleTest {


    private final SessionFactory sessionFactory;

    public RoleTest() {
        this.sessionFactory = HibernateUtil.getSessionFactory();
    }

    @BeforeEach
    public void init() {

    }

    @Test
    void findAll_WhenRolesAreGiven_ShouldReturnExactSize() {
        @Cleanup Session session = sessionFactory.openSession();

        var transaction = session.beginTransaction();
        List<Role> roles = new ArrayList<>();
        // when
        session.save(ADMIN);
        // then
        session.clear();

        Role role = session.get(Role.class, 1);
        roles.add(role);
        transaction.rollback();
        assertThat(roles).hasSize(1);
    }

    @Test
    void updateRole_WhenRoleIsGiven_ShouldReturnUpdatedRole() {
        @Cleanup Session session = sessionFactory.openSession();

        var transaction = session.beginTransaction();
        // when
        session.save(ADMIN);
        ADMIN.setDescription("new description");
        // then
        session.flush();

        Role role = session.get(Role.class, 1);
        transaction.rollback();
        assertThat(role.getDescription()).isEqualTo("new description");
    }

    @AfterEach
    public void close() {
        sessionFactory.close();
    }

}