package com.cortestudios.todoapp.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserRegisterDTO {

    @NotNull(message = "The firstName is required")
    @NotEmpty(message = "The firstName cant be empty")
    private String firstName;
    @NotNull(message = "The lastName is required")
    @NotEmpty(message = "The lastName cant be empty")
    private String lastName;
    @NotNull(message = "The email is required")
    @NotEmpty(message = "The email cant be empty")
    @Email(message = "This field must be an email")
    private String email;
    @NotNull(message = "The password is required")
    @NotEmpty(message = "The password cant be empty")
    private String password;
}
