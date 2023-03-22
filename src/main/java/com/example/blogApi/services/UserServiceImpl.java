package com.example.blogApi.services;

import com.example.blogApi.config.AppConstants;
import com.example.blogApi.entity.Role;
import com.example.blogApi.entity.User;
import com.example.blogApi.exceptions.ResourceNotFoundException;
import com.example.blogApi.payloads.UserDto;
import com.example.blogApi.repositories.RoleRepository;
import com.example.blogApi.repositories.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private RoleRepository roleRepository;

    @Override
    public UserDto registerUser(UserDto userDto) {
        User user = modelMapper.map(userDto,User.class);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        Role role = roleRepository.findById(AppConstants.NORMAL_USER).get();
        user.setRoles(Set.of(role));
        User savedUser = userRepository.save(user);
        return modelMapper.map(savedUser,UserDto.class);
    }

    @Override
    public UserDto createUser(UserDto userDto) {
        userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
        User user = userRepository.save(this.dtoToUser(userDto));
        return this.userToDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {
        User selectedUser = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        selectedUser.setName(userDto.getName());
        selectedUser.setEmail(userDto.getEmail());
        selectedUser.setPassword(userDto.getPassword());
        selectedUser.setAbout(userDto.getAbout());
        User updatedUser = userRepository.save(selectedUser);

        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        return this.userToDto(user);
    }

    @Override
    public void deleteUser(Integer id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "Id", id));
        userRepository.delete(user);
    }

    @Override
    public List<UserDto> getAllUsers() {
        List<User> users = userRepository.findAll();
        List<UserDto> userDtos = users.stream().map(user -> userToDto(user)).collect(Collectors.toList());
        return userDtos;
    }


    public User dtoToUser(UserDto userDto) {
        User user = modelMapper.map(userDto, User.class);

        return user;

    }

    public UserDto userToDto(User user) {
        UserDto userDto = modelMapper.map(user, UserDto.class);

        return userDto;
    }


}
