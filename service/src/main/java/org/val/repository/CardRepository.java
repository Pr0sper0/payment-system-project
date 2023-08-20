package org.val.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.val.entity.Card;

public interface CardRepository extends JpaRepository<Card, Long> {

}
