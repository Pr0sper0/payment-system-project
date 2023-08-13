package org.val.repository;


import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.val.entity.Account;

public class AccountRepository extends AbstractRepository<Long, Account> {

    private Session session;
    public AccountRepository(Session session) {
        super(Account.class, session);
    }

    public List<Account> findAllWhereCreatedAfter(Session session, String date) {
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        var criteria = criteriaBuilder.createQuery(Account.class);
        Root<Account> accountRoot = criteria.from(Account.class);
        Predicate predicate = criteriaBuilder.greaterThan(accountRoot.get("createdAt"),
                LocalDateTime.parse(date));
        criteria.where(predicate);
        return session.createQuery(criteria).getResultList();
    }
}