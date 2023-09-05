package org.val.integration.repository;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.val.dto.RoleReadDto;
import org.val.integration.IntegrationTestBase;
import org.val.service.RoleService;

@RequiredArgsConstructor
public class RoleServiceIT extends IntegrationTestBase {

    private static final Integer ROLE_1 = 1;

    private final RoleService roleService;

    @Test
    void testFindAll_WhenRolesAreGiven_ShouldReturnExactSize() {
        assertThat(roleService.findAll()).hasSize(2);
    }

    @Test
    void testFindById_WhenRoleHasAdded_ShouldReturnRole() {
        Optional<RoleReadDto> maybeRole = roleService.findById(ROLE_1);
        assertThat(maybeRole).isPresent();
        assertThat(maybeRole.get().role()).isEqualTo("ADMIN");
    }

}
