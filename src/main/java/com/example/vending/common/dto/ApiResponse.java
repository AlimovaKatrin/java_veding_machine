package com.example.vending.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiResponse<T> {
    private String status;
    private T data;
    private String message;

    public static <T> ApiResponse<T> success(T data) {
        return new ApiResponse<>("success", data, null);
    }

    public static <T> ApiResponse<T> error(String message) {
        return new ApiResponse<>("error", null, message);
    }

    public ResponseEntity<ApiResponse<T>> toResponse(HttpStatus status) {
        return ResponseEntity.status(status).body(this);
    }

    public ResponseEntity<ApiResponse<T>> toOk() {
        return ResponseEntity.ok(this);
    }
}
