package org.val.dao;

import static org.assertj.core.api.Assertions.assertThat;
import static org.val.integration.util.TestObjects.ADMIN;
import static org.val.integration.util.TestObjects.IVAN;
import static org.val.integration.util.TestObjects.PETR;
import static org.val.integration.util.TestObjects.USER;

import java.math.BigDecimal;
import java.sql.SQLOutput;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import lombok.Cleanup;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.graph.GraphSemantic;
import org.hibernate.graph.RootGraph;
import org.hibernate.type.LocalDateTimeType;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.val.entity.Account;
import org.val.entity.Card;
import org.val.util.HibernateUtil;

public class AccountDaoTest {

    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();

    private AccountDao accountDao = AccountDao.getInstance();

    @BeforeEach()
    public void init(){
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();
        LocalDateTime created1 = LocalDateTime.of(2021, 1, 22, 9, 1, 0);
        LocalDateTime created2 = LocalDateTime.of(2019, 1, 22, 9, 1, 0);

        LocalDateTime updated = LocalDateTime.of(2021, 1, 22, 9, 1, 0);

        session.save(ADMIN);
        session.save(USER);
        session.save(IVAN);
        session.save(PETR);
        session.save(new Card(1, "1234567890123456", "123", LocalDate.now(), created1, updated, null, null));

        session.save(new Account(1, "visa", new BigDecimal("123.00"), "USD", created1,
                updated, IVAN, null));
        session.save(new Account(2, "mastercard", new BigDecimal("123.00"), "USD", created2, updated,
                PETR, null));

       session.getTransaction().commit();
    }

    @AfterEach
    public void destroy(){
        @Cleanup Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        accountDao.deleteAll();
        transaction.commit();
        sessionFactory.close();
    }

    @Test
    void findAllWhenCreatedAfter_WhenOnlyOneAccountCreatedAfterDate_ShouldReturnExactOne() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        List<Account> resultList = accountDao.findAllWhereCreatedAfter("2020-01-01T00:00:00");

        assertThat(resultList).hasSize(1);

        session.getTransaction().rollback();
    }

    @Test
    void findAllAccountsWithUserAndCards_WhenGivenUserAndCards_ShouldReturnExactSize() {
        @Cleanup Session session = sessionFactory.openSession();
        session.beginTransaction();

        RootGraph<?> graph = session.getEntityGraph("AccountWithUserAndCards");

        Map<String, Object> properties = Map.of(GraphSemantic.LOAD.getJpaHintName(), graph);
        var account = session.find(Account.class, 27, properties);
        System.out.println(account);
        assertThat(account).isNotNull();

        session.getTransaction().rollback();
    }

}
