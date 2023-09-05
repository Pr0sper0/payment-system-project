package org.val.dtoMapper;

import org.springframework.stereotype.Component;
import org.val.dto.RoleReadDto;
import org.val.entity.Role;

@Component
public class RoleReadMapper implements Mapper<Role, RoleReadDto> {

    @Override
    public RoleReadDto map(Role from) {
        return new RoleReadDto(
            from.getId(),
            from.getRole(),
            from.getDescription(),
            from.getCreatedAt(),
            from.getUpdatedAt()
        );
    }
}
