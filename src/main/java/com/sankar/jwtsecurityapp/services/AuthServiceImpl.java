package com.sankar.jwtsecurityapp.services;

import com.sankar.jwtsecurityapp.dtos.SignupDTO;
import com.sankar.jwtsecurityapp.dtos.UserDTO;
import com.sankar.jwtsecurityapp.enities.User;
import com.sankar.jwtsecurityapp.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDTO createUser(SignupDTO signupDTO) {
        User user = new User();
        user.setEmail(signupDTO.getEmail());
        user.setName(signupDTO.getName());
        user.setPassword(new BCryptPasswordEncoder().encode(signupDTO.getPassword()));
        User createdUser = userRepository.save(user);
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(createdUser.getEmail());
        userDTO.setName(createdUser.getName());
        return userDTO;
    }
}
