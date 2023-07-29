package org.val.dao;


import static org.val.entity.QAccount.account;

import com.querydsl.jpa.impl.JPAQuery;
import java.util.List;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import org.hibernate.Session;
import org.val.entity.Account;


public class AccountDao extends AbstractDao<Long, Account> {

    public AccountDao() {
        super(Account.class);
    }

    public static AccountDao getInstance() {
        return new AccountDao();
    }

    @Override
    public List<Account> findAll() {
        Session session = sessionFactory.openSession();
        List<Account> list = new JPAQuery<Account>(session).select(account).from(account).fetch();
        session.close();
        return list;
    }

    public List<Account> findAllWhereCreatedAfter(String date) {
        Session session = sessionFactory.openSession();
        List<Account> list = new JPAQuery<Account>(session).select(account).from(account)
                .where(account.createdAt.after(java.time.LocalDateTime.parse(date))).fetch();
        session.close();
        return list;
    }

    public void deleteAll() {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Query query = session.getNamedQuery("Account.deleteAllRows");
        query.executeUpdate();
        session.close();
    }
}