package com.cortestudios.todoapp.dto.response;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class StandardResponse {

    private String statusCode;
    private String statusMessage;

}