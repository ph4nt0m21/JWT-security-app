package com.sankar.jwtsecurityapp.controllers;

import com.sankar.jwtsecurityapp.dtos.SignupDTO;
import com.sankar.jwtsecurityapp.dtos.UserDTO;
import com.sankar.jwtsecurityapp.services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SignupUserController {

    @Autowired
    private AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<?> createUser(@RequestBody SignupDTO signupDTO){
        UserDTO createdUser = authService.createUser(signupDTO);
        if(createdUser == null)
            return new ResponseEntity<>("User is not created, try again.", HttpStatus.BAD_REQUEST);
        return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
    }
}
