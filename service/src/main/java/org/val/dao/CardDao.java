package org.val.dao;

import org.hibernate.SessionFactory;
import org.val.entity.Card;

public class CardDao extends AbstractDao<Long, Card> {

    private SessionFactory sessionFactory;

    private CardDao(Class<Card> clazz) {
        super(clazz, null);
    }

    public static CardDao getInstance() {
        return new CardDao(Card.class);
    }
}
