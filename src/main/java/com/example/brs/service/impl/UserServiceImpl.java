package com.example.brs.service.impl;

import com.example.brs.dto.entity.user.UserDto;
import com.example.brs.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
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
        return null;
    }

    /**
     * @param userDto
     * @return
     */
    @Override
    public UserDto updateProfile(UserDto userDto) {
        return null;
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
