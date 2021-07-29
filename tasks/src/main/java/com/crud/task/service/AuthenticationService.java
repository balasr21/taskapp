package com.crud.task.service;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.crud.task.model.AuthenticationRequest;
import com.crud.task.model.AuthenticationResponse;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final UserService userService;
    private final JWTService jwtService;

    public AuthenticationResponse createToken(AuthenticationRequest authenticationRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getUserName(), authenticationRequest.getPassword()));
        final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUserName());
        return AuthenticationResponse.builder().jwtToken(jwtService.generateToken(userDetails)).expiryIn(jwtService.getTokenExpirationDuration()).build();
    }


}
