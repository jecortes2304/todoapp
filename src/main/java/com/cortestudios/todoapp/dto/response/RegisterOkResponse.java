package com.cortestudios.todoapp.dto.response;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@Builder
public class RegisterOkResponse{
    private String email;
    private String firstName;
    private String lastName;
    private String password;
}
