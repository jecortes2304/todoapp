package com.cortestudios.todoapp.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class StandardOkResponse extends StandardResponse{
    private Object result;
}
