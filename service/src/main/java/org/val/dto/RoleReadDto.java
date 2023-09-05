package org.val.dto;

import java.time.LocalDateTime;

public record RoleReadDto(Integer id, String role, String description, LocalDateTime createdAt,
                          LocalDateTime updatedAt) {

}
