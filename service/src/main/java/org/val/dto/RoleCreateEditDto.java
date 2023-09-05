package org.val.dto;

import java.time.LocalDateTime;
import lombok.Value;

@Value
public class RoleCreateEditDto {

    String role;

    String description;

    LocalDateTime createdAt;

    LocalDateTime updatedAt;
}
