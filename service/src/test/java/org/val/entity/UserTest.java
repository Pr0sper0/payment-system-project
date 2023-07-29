package org.val.entity;

import static org.junit.jupiter.api.Assertions.*;
import static org.val.integration.util.TestObjects.ADMIN;
import static org.val.integration.util.TestObjects.IVAN;
import static org.val.integration.util.TestObjects.PETR;
import static org.val.integration.util.TestObjects.SERGEY;
import static org.val.integration.util.TestObjects.USER;

import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.junit.Before;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.val.util.HibernateUtil;

class UserTest {

    SessionFactory sessionFactory;

    public UserTest() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }

    @BeforeEach
    public void init() {

    }

    @Test
    void findAll_WhenUsersAreGiven_ShouldReturnExactSize() {
        // given
        @Cleanup Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        // when

        session.save(ADMIN);
        session.save(USER);

        session.save(IVAN);
        session.save(SERGEY);
        session.save(PETR);
        // then

        assertEquals(3, session.createQuery("from User").list().size());
        transaction.rollback();
    }

    @AfterEach
    public void close() {
        sessionFactory.close();
    }

}