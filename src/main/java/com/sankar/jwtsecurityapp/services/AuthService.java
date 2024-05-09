package com.sankar.jwtsecurityapp.services;

import com.sankar.jwtsecurityapp.dtos.SignupDTO;
import com.sankar.jwtsecurityapp.dtos.UserDTO;

public interface AuthService {
    UserDTO createUser(SignupDTO signupDTO);
}
