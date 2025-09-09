package com.client.ws.rasmooplus.exception.handler;

import com.client.ws.rasmooplus.dto.error.ErrorResponseDto;
import com.client.ws.rasmooplus.exception.BadRequestException;
import com.client.ws.rasmooplus.exception.BusinessException;
import com.client.ws.rasmooplus.exception.NotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ResourceHandler {
    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<ErrorResponseDto> notFoundException(NotFoundException nfe){
        String errorMessage = nfe.getMessage();
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ErrorResponseDto.builder()
                        .message(nfe.getMessage())
                        .httpStatus(HttpStatus.NOT_FOUND)
                        .statusCode(HttpStatus.NOT_FOUND.value())
                        .build());
    }


    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<ErrorResponseDto>badRequestException(BadRequestException bre){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto.builder()

               .message(bre.getMessage())
               .httpStatus(HttpStatus.BAD_REQUEST)
               .statusCode(HttpStatus.BAD_REQUEST.value())
               .build());
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ErrorResponseDto>badRequestException(BusinessException be){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ErrorResponseDto.builder()

                .message(be.getMessage())
                .httpStatus(HttpStatus.CONFLICT)
                .statusCode(HttpStatus.CONFLICT.value())
                .build());
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponseDto> badRequestException(MethodArgumentNotValidException mnve){
       
        Map<String, String>messages = new HashMap<>();
        mnve.getBindingResult().getAllErrors().forEach(error -> {
            String field = ((FieldError)error).getField();
            String defaultMessage = error.getDefaultMessage();
            messages.put(field , defaultMessage);
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto.builder()
                .message(Arrays.toString(messages.entrySet().toArray()))
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build());
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorResponseDto>badRequestException(DataIntegrityViolationException dive){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ErrorResponseDto.builder()

                .message(dive.getMessage())
                .httpStatus(HttpStatus.BAD_REQUEST)
                .statusCode(HttpStatus.BAD_REQUEST.value())
                .build());
    }




}
