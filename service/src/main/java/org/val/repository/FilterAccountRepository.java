package org.val.repository;

import java.time.LocalDateTime;
import java.util.List;
import org.val.entity.Account;

public interface FilterAccountRepository {

    List<Account> findAllWhereCreatedAfter(LocalDateTime date);
}
