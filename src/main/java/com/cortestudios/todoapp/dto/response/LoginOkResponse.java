package com.cortestudios.todoapp.dto.response;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class LoginOkResponse{
    private String token;
}
