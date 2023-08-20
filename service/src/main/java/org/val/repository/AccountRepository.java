package org.val.repository;


import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.val.entity.Account;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer> {
    default public List<Account> findAllWhereCreatedAfter(Session session, String date) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        var criteria = criteriaBuilder.createQuery(Account.class);
        Root<Account> accountRoot = criteria.from(Account.class);
        Predicate predicate = criteriaBuilder.greaterThan(accountRoot.get("createdAt"),
                LocalDateTime.parse(date));
        criteria.where(predicate);
        return session.createQuery(criteria).getResultList();
    }
}