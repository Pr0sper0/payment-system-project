package org.val.repository;

import org.hibernate.Session;
import org.val.entity.Card;

public class CardRepository extends AbstractRepository<Long, Card> {

    private CardRepository(Session session) {
        super(Card.class, session);
    }

    public static CardRepository getInstance(Session session) {
        return new CardRepository(session);
    }
}
