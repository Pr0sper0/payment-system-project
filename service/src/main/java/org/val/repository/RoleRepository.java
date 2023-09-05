package org.val.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.val.entity.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Integer> {
}
