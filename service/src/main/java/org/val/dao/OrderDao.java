package org.val.dao;

import org.hibernate.SessionFactory;
import org.val.entity.Order;

public class OrderDao extends AbstractDao<Long, Order> {

    private OrderDao(Class<Order> clazz) {
        super(clazz);
    }

    public static OrderDao getInstance() {
        return new OrderDao(Order.class);
    }


}