package org.val.integration.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.val.integration.util.TestObjects.ADMIN;
import static org.val.integration.util.TestObjects.USER;

import java.util.List;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.val.repository.RoleRepository;
import org.val.entity.Role;
import org.val.integration.IntegrationTestBase;
import org.val.util.HibernateUtil;

public class RoleDaoIT extends IntegrationTestBase {

    RoleRepository roleDao;
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    @BeforeEach
    public void init() {


    }

//    @AfterAll
//    public static void close() {
//
//    }

    @BeforeEach
    public void initializeDatabase() {
        @Cleanup Session session = sessionFactory.openSession();
        roleDao = RoleRepository.getInstance(session);

        Transaction transaction = session.beginTransaction();

        List<Role> roleList = List.of(ADMIN, USER);
        roleList.forEach(session::save);

        session.getTransaction().commit();

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
