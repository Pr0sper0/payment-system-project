package org.val.repository;

import org.hibernate.Session;
import org.val.entity.Order;

public class OrderRepository extends AbstractRepository<Long, Order> {

    private OrderRepository(Session session) {
        super(Order.class, session);
    }

    public static OrderRepository getInstance(Session session) {
        return new OrderRepository(session);
    }


}