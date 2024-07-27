package com.cortestudios.todoapp.exception;

import java.util.ArrayList;
import java.util.List;

import com.cortestudios.todoapp.dto.response.StandardErrorResponse;
import com.cortestudios.todoapp.dto.response.StandardResponse;
import jakarta.el.MethodNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;

@ControllerAdvice
public class ValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> notValid(MethodArgumentNotValidException ex, HttpServletRequest request) {

        List<String> errors = new ArrayList<>();

        ex.getAllErrors().forEach(err -> errors.add(err.getDefaultMessage()));

        StandardResponse response = StandardErrorResponse.builder()
                .errors(errors)
                .statusCode(HttpStatus.BAD_REQUEST.name())
                .statusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> httpMessageNotReadableHandler(HttpMessageNotReadableException ex, HttpServletRequest request) {

        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage());

        StandardResponse response = StandardErrorResponse.builder()
                .errors(errors)
                .statusCode(HttpStatus.BAD_REQUEST.name())
                .statusMessage(HttpStatus.BAD_REQUEST.getReasonPhrase())
                .build();

        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodNotFoundException.class)
    public ResponseEntity<?> notFound(MethodNotFoundException ex, HttpServletRequest request) {

        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage() != null ? "Resource not found: " + ex.getMessage() : "Resource not found");

        StandardResponse response = StandardErrorResponse.builder()
                .errors(errors)
                .statusCode(HttpStatus.NOT_FOUND.name())
                .statusMessage(HttpStatus.NOT_FOUND.getReasonPhrase())
                .build();

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<?> userNotFound(UsernameNotFoundException ex, HttpServletRequest request) {

        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage() != null ? "Resource not found: " + ex.getMessage() : "Resource not found");

        StandardResponse response = StandardErrorResponse.builder()
                .errors(errors)
                .statusCode(HttpStatus.UNAUTHORIZED.name())
                .statusMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HttpClientErrorException.Unauthorized.class)
    public ResponseEntity<?> unauthorizedError(HttpClientErrorException.Unauthorized ex,
                                               HttpServletRequest request) {

        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage() != null ? "Resource unauthorized: " + ex.getMessage() : "Resource unauthorized");

        StandardResponse response = StandardErrorResponse.builder()
                .errors(errors)
                .statusCode(HttpStatus.UNAUTHORIZED.name())
                .statusMessage(HttpStatus.UNAUTHORIZED.getReasonPhrase())
                .build();

        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(HttpClientErrorException.Forbidden.class)
    public ResponseEntity<?> forbidden(HttpClientErrorException.Forbidden ex,
                                               HttpServletRequest request) {

        List<String> errors = new ArrayList<>();
        errors.add(ex.getMessage() != null ? "Resource forbidden: " + ex.getMessage() : "Resource forbidden");

        StandardResponse response = StandardErrorResponse.builder()
                .errors(errors)
                .statusCode(HttpStatus.FORBIDDEN.name())
                .statusMessage(HttpStatus.FORBIDDEN.getReasonPhrase())
                .build();

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}