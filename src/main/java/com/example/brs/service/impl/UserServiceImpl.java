package com.example.brs.service.impl;

import com.example.brs.dto.entity.user.UserDto;
import com.example.brs.dto.mapper.UserMapper;
import com.example.brs.entity.user.User;
import com.example.brs.exception.EntityType;
import com.example.brs.exception.ExceptionType;
import com.example.brs.exception.StandardExceptionMessage;
import com.example.brs.repository.user.RoleRepository;
import com.example.brs.repository.user.UserRepository;
import com.example.brs.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
public class UserServiceImpl implements UserService, StandardExceptionMessage {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

//    private final BCry


    /**
     * @param userDto
     * @return
     */
    @Override
    public UserDto signup(UserDto userDto) {
        return null;
    }

    /**
     * @param email
     * @return
     */
    @Override
    public UserDto findUserByEmail(String email) {
        User user = Optional.of(userRepository.findByEmail(email))
                .orElseThrow(() -> exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, email));
        return UserMapper.toUserDto(user);
    }

    /**
     * @param userDto
     * @return
     */
    @Override
    public UserDto updateProfile(UserDto userDto) {
        String email = userDto.getEmail();
        User userToUpdate = Optional.of(userRepository.findByEmail(email))
                .orElseThrow(() -> exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, email));

        userToUpdate.setFirstName(userDto.getFirstName());
        userToUpdate.setLastName(userDto.getLastName());
        userToUpdate.setMobileNumber(userDto.getMobileNumber());
        return UserMapper.toUserDto(userRepository.save(userToUpdate));
    }

    /**
     * @param userDto
     * @param newPassword
     * @return
     */
    @Override
    public UserDto changePassword(UserDto userDto, String newPassword) {
        return null;
    }
}
