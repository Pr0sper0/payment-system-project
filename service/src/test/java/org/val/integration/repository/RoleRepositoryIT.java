package org.val.integration.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.val.integration.util.TestObjects.USER;

import java.util.List;
import javax.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.val.entity.Role;
import org.val.integration.IntegrationTestBase;
import org.val.repository.RoleRepository;


public class RoleRepositoryIT extends IntegrationTestBase {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private EntityManager entityManager;

//    @BeforeEach
//    public void initializeDatabase() {
//        List<Role> roleList = List.of(ADMIN, USER);
//        roleList.forEach(entityManager::persist);
//    }

    @Test
    void testFindAll_WhenRolesAreGiven_ShouldReturnExactSize() {
        List<Role> all = roleRepository.findAll();
        assertThat(all).hasSize(2);
    }

    @Test
    void testFindById_WhenRoleHasAdded_ShouldReturnRole() {
        Role role = roleRepository.findById((long) USER.getId()).orElseThrow();

        assertThat(role).isEqualTo(USER);
    }
}
