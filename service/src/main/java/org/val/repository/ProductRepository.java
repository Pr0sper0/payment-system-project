package org.val.repository;

import org.hibernate.Session;
import org.val.entity.Product;

public class ProductRepository extends AbstractRepository<Integer, Product> {

    private ProductRepository(Session session) {
        super(Product.class, session);
    }

    public static ProductRepository getInstance(Session session) {
        return new ProductRepository(session);
    }
}
