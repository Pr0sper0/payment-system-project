package org.val.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.val.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

}
