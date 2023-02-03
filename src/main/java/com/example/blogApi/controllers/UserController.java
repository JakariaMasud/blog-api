package com.example.blogApi.controllers;

import com.example.blogApi.payloads.ApiResponse;
import com.example.blogApi.payloads.UserDto;
import com.example.blogApi.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {
    @Autowired
    private  UserService userService;

    @PostMapping("/")
    ResponseEntity<UserDto> createUser(@Valid @RequestBody UserDto userDto){
        UserDto createdUserDto = userService.createUser(userDto);
        return new ResponseEntity<>(createdUserDto, HttpStatus.CREATED);

    }
    @PutMapping("/{userId}")
    ResponseEntity<UserDto> updateUser(@Valid @RequestBody UserDto userDto,@PathVariable("userId") Integer id){
       UserDto updatedUser=userService.updateUser(userDto,id);
       return new ResponseEntity<>(updatedUser,HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ApiResponse> deleteUser(@PathVariable("id") Integer id){
        userService.deleteUser(id);
        ApiResponse response = new ApiResponse("User Deleted Successfully",true);
        return new ResponseEntity<>(response,HttpStatus.OK);
    }

    @GetMapping("/")
    ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users=userService.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }
    @GetMapping("/{userId}")
    ResponseEntity<UserDto> getUserById(@PathVariable("userId") Integer id){
        return new ResponseEntity<>(userService.getUserById(id),HttpStatus.OK);
    }
}
