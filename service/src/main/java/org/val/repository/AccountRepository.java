package org.val.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.val.entity.Account;

@Repository
public interface AccountRepository extends
        JpaRepository<Account, Integer>,
        FilterAccountRepository {
}