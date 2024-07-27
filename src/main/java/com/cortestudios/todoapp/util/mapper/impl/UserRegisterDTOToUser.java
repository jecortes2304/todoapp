package com.cortestudios.todoapp.util.mapper.impl;

import com.cortestudios.todoapp.dto.RoleDTO;
import com.cortestudios.todoapp.dto.UserRegisterDTO;
import com.cortestudios.todoapp.persistence.entity.User;
import com.cortestudios.todoapp.util.mapper.IMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UserRegisterDTOToUser implements IMapper<UserRegisterDTO, User> {
    private final PasswordEncoder passwordEncoder;

    @Override
    public User map(UserRegisterDTO in) {
        return User.builder()
                .email(in.getEmail())
                .firstName(in.getFirstName())
                .lastName(in.getLastName())
                .password(passwordEncoder.encode(in.getPassword()))
                .role(RoleDTO.USER)
                .build();
    }
}
