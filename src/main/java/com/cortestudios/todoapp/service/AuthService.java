package com.cortestudios.todoapp.service;

import com.cortestudios.todoapp.dto.*;
import com.cortestudios.todoapp.dto.response.LoginOkResponse;
import com.cortestudios.todoapp.dto.response.RegisterOkResponse;
import com.cortestudios.todoapp.persistence.entity.User;
import com.cortestudios.todoapp.persistence.repository.UserRepository;
import com.cortestudios.todoapp.util.mapper.impl.UserRegisterDTOToUser;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserRepository userRepository;
    private final UserRegisterDTOToUser userRegisterDTOToUser;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;


    public RegisterOkResponse register(UserRegisterDTO userRegisterDTO) {
        userRepository.findByEmail(userRegisterDTO.getEmail()).orElseThrow();
        User user = userRegisterDTOToUser.map(userRegisterDTO);
        userRepository.save(user);
        return RegisterOkResponse.builder()
                .email(user.getEmail())
                .password(userRegisterDTO.getPassword())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .build();
    }

    public LoginOkResponse login(UserLoginDTO userLoginDTO) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        userLoginDTO.getEmail(),
                        userLoginDTO.getPassword()
                )
        );
        User user = userRepository.findByEmail(userLoginDTO.getEmail())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        String jwt = jwtService.generateToken(user);
        return LoginOkResponse.builder()
                .token(jwt)
                .build();
    }





}
