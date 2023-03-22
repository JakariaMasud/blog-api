package com.example.blogApi.services;

import com.example.blogApi.payloads.UserDto;

import java.util.List;

public interface UserService {
    UserDto registerUser(UserDto userDto);

    UserDto createUser(UserDto userDto);
    UserDto updateUser(UserDto userDto,Integer id);

    UserDto getUserById(Integer id);

    void deleteUser(Integer id);
    List<UserDto> getAllUsers();
}
