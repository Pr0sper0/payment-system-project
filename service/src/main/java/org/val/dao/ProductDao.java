package org.val.dao;

import org.hibernate.SessionFactory;
import org.val.entity.Product;

public class ProductDao extends AbstractDao<Long, Product> {

    private SessionFactory sessionFactory;

    private ProductDao(Class<Product> clazz) {
        super(clazz, null);
    }

    public static ProductDao getInstance() {
        return new ProductDao(Product.class);
    }
}
