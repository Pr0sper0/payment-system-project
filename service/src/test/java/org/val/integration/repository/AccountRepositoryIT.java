package org.val.integration.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;
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
    private AccountRepository accountRepository;
    
    @Test
    void testFindAllWhereCreatedAfter() {

        List<Account> accounts = accountRepository.findAllWhereCreatedAfter(LocalDateTime.of(2023, 8, 1, 0, 0, 0));

        assertEquals(2, accounts.size());
    }
}