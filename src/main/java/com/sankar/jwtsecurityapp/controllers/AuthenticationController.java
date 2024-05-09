package com.sankar.jwtsecurityapp.controllers;

import com.sankar.jwtsecurityapp.dtos.AuthenticationDTO;
import com.sankar.jwtsecurityapp.dtos.AuthenticationResponse;
import com.sankar.jwtsecurityapp.services.jwt.UserDetailsServiceImpl;
import com.sankar.jwtsecurityapp.utils.JwtUtil;
import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AuthenticationController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsServiceImpl userDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping("/authenticate")
    public AuthenticationResponse createAuthenticationToken(@RequestBody AuthenticationDTO authenticationDTO, HttpServletResponse response) throws BadCredentialsException, DisabledException, UsernameNotFoundException, IOException, java.io.IOException {
        try{
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationDTO.getEmail(), authenticationDTO.getPassword()));
        }catch(BadCredentialsException e){
            throw new BadCredentialsException("Incorrect username or password.");
        }catch(DisabledException disabledException){
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "user is not created. Register user first.");
            return null;
        }
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationDTO.getEmail());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());
        return new AuthenticationResponse(jwt);
    }
}
