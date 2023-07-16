package org.val.integration.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.val.integration.util.TestObjects.ADMIN;
import static org.val.integration.util.TestObjects.USER;
import static org.val.util.HibernateUtil.buildConfiguration;

import java.util.List;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.val.dao.RoleDao;
import org.val.entity.Role;
import org.val.integration.IntegrationTestBase;

public class RoleDaoIT extends IntegrationTestBase {

    RoleDao roleDao;
    private SessionFactory sessionFactory;

    @BeforeEach
    public void init() {
        Configuration cfg = buildConfiguration().configure();
        sessionFactory = cfg.buildSessionFactory();
        roleDao = RoleDao.getInstance(sessionFactory);

    }

//    @AfterAll
//    public static void close() {
//
//    }

    @BeforeEach
    public void initializeDatabase() {
        roleDao = RoleDao.getInstance(sessionFactory);

        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Role> roleList = List.of(ADMIN, USER);
        roleList.forEach(session::save);

        transaction.commit();
        session.close();
    }

    @AfterEach
    public void close() {
        sessionFactory.close();
    }


    @Test
    void testFindAll_WhenRolesAreGiven_ShouldReturnExactSize() {
        @Cleanup Session session = sessionFactory.openSession();
        List<Role> all = roleDao.findAll();
        assertThat(all).hasSize(2);
    }

    @Test
    void testFindById_WhenRoleHasAdded_ShouldReturnRole() {
        @Cleanup Session session = sessionFactory.openSession();

        Role role = roleDao.findById((long) USER.getId()).get();

        assertThat(role).isEqualTo(USER);
    }
}
