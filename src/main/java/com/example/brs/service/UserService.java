package com.example.brs.service;

import com.example.brs.dto.entity.user.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto signup(UserDto userDto);
    UserDto findUserByEmail(String email);
    UserDto updateProfile(UserDto userDto);
    UserDto changePassword(UserDto userDto, String newPassword);
}
