package com.example.brs.dto.mapper;

import com.example.brs.dto.entity.user.UserDto;
import com.example.brs.entity.user.User;

import java.util.stream.Collectors;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        return UserDto.builder()
                .withEmail(user.getEmail())
                .withFirstName(user.getFirstName())
                .withLastName(user.getLastName())
                .withMobileNumber(user.getMobileNumber())
                .withRoles(user.getRoles().stream()
                        .map(RoleMapper::toRoleDto)
                        .collect(Collectors.toSet()))
                .build();
    }
}
