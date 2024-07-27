package com.cortestudios.todoapp.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TaskCreateDTO {

    @NotNull(message = "The title is required")
    @NotEmpty(message = "The title cant be empty")
    private String title;

    @NotNull(message = "The description is required")
    @NotEmpty(message = "The description cant be empty")
    private String description;
    private LocalDateTime endedDate;

}
