package com.cortestudios.todoapp.dto.response;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
public class StandardErrorResponse extends StandardResponse{
    private List<String> errors;
}
