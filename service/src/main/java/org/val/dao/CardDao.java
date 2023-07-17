package org.val.dao;

import org.hibernate.SessionFactory;
import org.val.entity.Card;

public class CardDao extends AbstractDao<Long, Card> {

    private CardDao(Class<Card> clazz) {
        super(clazz);
    }

    public static CardDao getInstance() {
        return new CardDao(Card.class);
    }
}
