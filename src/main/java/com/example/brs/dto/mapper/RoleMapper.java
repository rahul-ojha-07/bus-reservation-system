package com.example.brs.dto.mapper;

import com.example.brs.dto.entity.user.RoleDto;
import com.example.brs.entity.user.Role;

public class RoleMapper {
    public static RoleDto toRoleDto(Role role) {
        return RoleDto.builder()
                .withRole(role.getRole().name())
                .build();
    }
}
