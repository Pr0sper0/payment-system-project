package org.val.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.val.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}