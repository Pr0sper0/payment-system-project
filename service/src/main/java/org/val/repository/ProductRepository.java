package org.val.repository;

import org.hibernate.Session;
import org.val.entity.Product;

public class ProductRepository extends AbstractRepository<Integer, Product> {

    public ProductRepository(Session session) {
        super(Product.class, session);
    }
}
