package com.cortestudios.todoapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserLoginDTO {

    @NotNull(message = "The title is required")
    @NotEmpty(message = "The title cant be empty")
    @Email(message = "This field must be an email")
    private String email;
    @NotNull(message = "The description is required")
    @NotEmpty(message = "The description cant be empty")
    private String password;
}
