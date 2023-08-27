package org.val.repository;

import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.val.entity.Account;

@RequiredArgsConstructor
public class AccountRepositoryImpl implements FilterAccountRepository{

    @Autowired
    EntityManager entityManager;
    public List<Account> findAllWhereCreatedAfter(
            LocalDateTime date) {
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        var criteria = criteriaBuilder.createQuery(Account.class);
        Root<Account> accountRoot = criteria.from(Account.class);
        Predicate predicate = criteriaBuilder.greaterThan(accountRoot.get("createdAt"), date);
        criteria.where(predicate);
        return entityManager.createQuery(criteria).getResultList();
    }

}
