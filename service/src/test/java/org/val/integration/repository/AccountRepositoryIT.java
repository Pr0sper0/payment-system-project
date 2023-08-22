package org.val.integration.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.val.entity.Account;
import org.val.integration.IntegrationTestBase;
import org.val.repository.AccountRepository;

public class AccountRepositoryIT extends IntegrationTestBase {

    @Autowired
    private EntityManager entityManager;

    @Autowired
    private AccountRepository accountRepository;


    @Test
    public void testFindAllWhereCreatedAfter() {
        Account account1 = new Account();
        account1.setCreatedAt(LocalDateTime.of(2023, 8, 1, 12, 0, 0));

        Account account2 = new Account();
        account2.setCreatedAt(LocalDateTime.of(2023, 8, 2, 12, 0, 0));

        entityManager.persist(account1);
        entityManager.persist(account2);
        entityManager.flush();

        List<Account> accounts = accountRepository.findAllWhereCreatedAfter(entityManager, LocalDateTime.of(2023, 8, 1, 0, 0, 0));

        assertEquals(1, accounts.size());
        assertEquals(account2.getId(), accounts.get(0).getId());
    }
}