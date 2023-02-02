package com.example.blogApi.services;

import com.example.blogApi.entity.User;
import com.example.blogApi.exceptions.ResourceNotFoundException;
import com.example.blogApi.payloads.UserDto;
import com.example.blogApi.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDto createUser(UserDto userDto) {
        User user=userRepository.save(this.dtoToUser(userDto));
        return this.userToDto(user);
    }

    @Override
    public UserDto updateUser(UserDto userDto, Integer id) {
        User user = userRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","Id",id));
        User selectedUser = this.dtoToUser(userDto);
        User updatedUser=userRepository.save(selectedUser);

        return this.userToDto(updatedUser);
    }

    @Override
    public UserDto getUserById(Integer id) {
        User user = userRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("User","Id",id));

        return this.userToDto(user);
    }

    @Override
    public UserDto deleteUser(Integer id) {
        return null;
    }

    @Override
    public List<UserDto> getAllUsers() {
        return null;
    }


    public User dtoToUser(UserDto userDto){
        User user = new User();
        user.setId(userDto.getId());
        user.setName(userDto.getName());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setAbout(userDto.getAbout());
        return user;

    }
    public UserDto userToDto(User user){
        UserDto userDto=new UserDto();
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setAbout(user.getAbout());
        return userDto;
    }


}
