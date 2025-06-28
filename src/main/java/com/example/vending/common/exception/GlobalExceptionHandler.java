package com.example.vending.common.exception;

import com.example.vending.common.dto.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ApiResponse<Object>> handleAll(Exception ex) {
        return new ApiResponse<>("error", null, ex.getMessage())
                .toResponse(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
