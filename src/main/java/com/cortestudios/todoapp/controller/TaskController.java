package com.cortestudios.todoapp.controller;

import com.cortestudios.todoapp.dto.TaskCreateDTO;
import com.cortestudios.todoapp.dto.TaskStatusDTO;
import com.cortestudios.todoapp.dto.TaskUpdateDTO;
import com.cortestudios.todoapp.persistence.entity.Task;
import com.cortestudios.todoapp.service.TaskService;
import com.cortestudios.todoapp.dto.response.StandardErrorResponse;
import com.cortestudios.todoapp.dto.response.StandardOkResponse;
import com.cortestudios.todoapp.dto.response.StandardResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api/v1/tasks")
@Tag(name = "Tasks")
@RequiredArgsConstructor
public class TaskController {

    private final TaskService taskService;

    @PutMapping(path = "/task",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Create a task", description = "Create a task given a payload")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Task created successfully",
                    content = @Content(schema = @Schema(implementation = StandardOkResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = StandardErrorResponse.class)))
    })
    public ResponseEntity<Object> createTask(@Valid @RequestBody TaskCreateDTO taskCreateDTO) {

        Task task = taskService.createTask(taskCreateDTO);

        StandardResponse response = StandardOkResponse.builder()
                .result(task)
                .statusCode(HttpStatus.CREATED.name())
                .statusMessage("Task created successfully")
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }



    @PatchMapping(path = "/task/{taskId}", produces= MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Update a task", description = "Update a task by an idTask and a taskUpdate payload")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task updated successfully",
                    content = @Content(schema = @Schema(implementation = StandardOkResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = StandardErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = StandardErrorResponse.class)))
    })
    public ResponseEntity<?> updateTask(@Valid @RequestBody TaskUpdateDTO taskUpdateDTO,
                                        @PathVariable("taskId") Long taskId,
                                        @RequestParam("taskStatus") TaskStatusDTO taskStatus) {
        Task task = taskService.updateTaskByStatusDto(taskUpdateDTO, taskStatus, taskId);

        StandardResponse response = StandardOkResponse.builder()
                .result(task)
                .statusCode(HttpStatus.OK.name())
                .statusMessage("Task updated successfully")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @DeleteMapping(path = "/task/{taskId}", produces= MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Delete a task", description = "Delete a task by an idTask")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task deleted successfully",
                    content = @Content(schema = @Schema(implementation = StandardOkResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not found",
                    content = @Content(schema = @Schema(implementation = StandardErrorResponse.class)))
    })
    public ResponseEntity<?> deleteTask(@PathVariable(value = "taskId") Long taskId) {

        taskService.deleteTaskById(taskId);

        StandardResponse response = StandardOkResponse.builder()
                .result(true)
                .statusCode(HttpStatus.OK.name())
                .statusMessage("Task deleted successfully")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @GetMapping(path = "/task/{taskId}", produces= MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get task by taskId", description = "Get task by taskId")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Task fetched successfully",
                    content = @Content(schema = @Schema(implementation = StandardOkResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = StandardErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "Not Found",
                    content = @Content(schema = @Schema(implementation = StandardErrorResponse.class)))
    })
    public ResponseEntity<?> getTaskById(@PathVariable(value = "taskId") Long taskId) {

        Task task = taskService.getTaskById(taskId);

        StandardResponse response = StandardOkResponse.builder()
                .result(task)
                .statusCode(HttpStatus.OK.name())
                .statusMessage("Task fetched successfully")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }



    @GetMapping(produces= MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Get all tasks", description = "Fetch all task by page")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Tasks fetched successfully",
                    content = @Content(schema = @Schema(implementation = StandardOkResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = StandardErrorResponse.class)))
    })
    public ResponseEntity<?> getAllTasks(@Min(5) @RequestParam(defaultValue = "5", required = false)
                                         Integer pageSize,
                                         @Min(0) @RequestParam(defaultValue = "0", required = false)
                                         Integer page) {
        Pageable paging = PageRequest.of(page, pageSize);

        List<Task> tasks = taskService.getAllTasks(paging);

        StandardResponse response = StandardOkResponse.builder()
                .result(tasks)
                .statusCode(HttpStatus.OK.name())
                .statusMessage("Tasks fetched successfully")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
