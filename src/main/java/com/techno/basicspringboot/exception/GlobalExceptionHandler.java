package com.techno.basicspringboot.exception;

import com.techno.basicspringboot.dto.BaseResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<BaseResponseDto> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(fieldError -> fieldError.getField() + ": " + fieldError.getDefaultMessage())
                .collect(Collectors.toList());
        return new ResponseEntity<>(
                BaseResponseDto.builder()
                        .status(HttpStatus.BAD_REQUEST)
                        .description(String.join(", ", errors))
                        .build(),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(HttpClientErrorException.class)
    public ResponseEntity<BaseResponseDto> handleHttpClientErrorException(HttpClientErrorException ex) {
        return new ResponseEntity<>(
                BaseResponseDto.builder()
                        .status((HttpStatus) ex.getStatusCode())
                        .description(ex.getMessage())
                        .build(),
                ex.getStatusCode()
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<BaseResponseDto> handleGenericException(Exception ex) {
        return new ResponseEntity<>(
                BaseResponseDto.builder()
                        .status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .description(ex.getMessage())
                        .build(),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }
}
