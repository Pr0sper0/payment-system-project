package org.val.dao;

import org.hibernate.SessionFactory;
import org.val.entity.Account;

public class AccountDao  extends AbstractDao<Long, Account> {

    private SessionFactory sessionFactory;

    private AccountDao(Class<Account> clazz) {
        super(clazz, null);
    }

    public static AccountDao getInstance() {
        return new AccountDao(Account.class);
    }
}