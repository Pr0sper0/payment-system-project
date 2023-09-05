package org.val.dtoMapper;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.val.dto.RoleCreateEditDto;
import org.val.entity.Role;

@Component
@RequiredArgsConstructor
public class RoleCreateEditMapper implements Mapper<RoleCreateEditDto, Role> {

    @Override
    public Role map(RoleCreateEditDto fromObject, Role toObject) {
        copy(fromObject, toObject);
        return toObject;
    }

    @Override
    public Role map(RoleCreateEditDto from) {
        Role role = new Role();
        copy(from, role);
        return role;
    }

    private static void copy(RoleCreateEditDto from, Role role) {
        role.setRole(from.getRole());
        role.setDescription(from.getDescription());
        role.setCreatedAt(from.getCreatedAt());
        role.setUpdatedAt(from.getUpdatedAt());
    }
}
