package com.cortestudios.todoapp.controller;


import com.cortestudios.todoapp.dto.UserLoginDTO;
import com.cortestudios.todoapp.dto.UserRegisterDTO;
import com.cortestudios.todoapp.dto.response.*;
import com.cortestudios.todoapp.service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@Tag(name = "Auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping(path = "/register",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Register an user", description = "Register an user given a payload")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User registered successfully",
                    content = @Content(schema = @Schema(implementation = StandardOkResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = StandardErrorResponse.class)))
    })
    public ResponseEntity<Object> register(@Valid @RequestBody UserRegisterDTO userRegisterDTO) {

        RegisterOkResponse registerOkResponse = authService.register(userRegisterDTO);

        StandardResponse response = StandardOkResponse.builder()
                .result(registerOkResponse)
                .statusCode(HttpStatus.CREATED.name())
                .statusMessage("User registered successfully")
                .build();

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }


    @PostMapping(path = "/login",
            consumes = MediaType.APPLICATION_JSON_VALUE, produces= MediaType.APPLICATION_JSON_VALUE)
    @Operation(summary = "Do login in", description = "Do login and receive accesses token")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User logged successfully",
                    content = @Content(schema = @Schema(implementation = StandardOkResponse.class))),
            @ApiResponse(responseCode = "400", description = "Bad request",
                    content = @Content(schema = @Schema(implementation = StandardErrorResponse.class))),
            @ApiResponse(responseCode = "401", description = "Unauthorized",
                    content = @Content(schema = @Schema(implementation = StandardErrorResponse.class)))
    })
    public ResponseEntity<Object> login(@Valid @RequestBody UserLoginDTO userLoginDTO) {

        LoginOkResponse loginOkResponse = authService.login(userLoginDTO);

        StandardResponse response = StandardOkResponse.builder()
                .result(loginOkResponse)
                .statusCode(HttpStatus.OK.name())
                .statusMessage("User logged successfully")
                .build();

        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
