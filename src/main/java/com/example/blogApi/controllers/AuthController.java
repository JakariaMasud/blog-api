package com.example.blogApi.controllers;

import com.example.blogApi.payloads.JwtAuthRequest;
import com.example.blogApi.payloads.JwtAuthResponse;
import com.example.blogApi.payloads.UserDto;
import com.example.blogApi.repositories.UserRepository;
import com.example.blogApi.security.JwtTokenHelper;
import com.example.blogApi.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private JwtTokenHelper jwtTokenHelper;
    @Autowired
    UserDetailsService userDetailsService;
    @Autowired
    AuthenticationManager authenticationManager;
    @Autowired
    UserService userService;
    @PostMapping("/login")
    ResponseEntity<JwtAuthResponse> createToken(@RequestBody JwtAuthRequest jwtAuthRequest) throws Exception {
        authenticate(jwtAuthRequest.getUserName(),jwtAuthRequest.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(jwtAuthRequest.getUserName());
        String token = jwtTokenHelper.generateToken(userDetails);
        JwtAuthResponse response = new JwtAuthResponse();
        response.setToken(token);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void authenticate(String userName, String password) throws Exception {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userName,password);
        try{
            authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        }catch (BadCredentialsException e){
            throw new Exception("invalid user name or password");
        }

    }
    @PostMapping("/register")
    ResponseEntity<UserDto> registerUser(@RequestBody UserDto userDto){
       UserDto registeredUser = userService.registerUser(userDto);
       return new ResponseEntity<>(registeredUser,HttpStatus.CREATED);

    }
}
