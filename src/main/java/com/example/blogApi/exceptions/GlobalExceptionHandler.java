package com.example.blogApi.exceptions;

import com.example.blogApi.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<ApiResponse> resourceNotFoundHandler(ResourceNotFoundException exception){
        String msg = exception.getMessage();
        ApiResponse apiResponse = new ApiResponse(msg,false);
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    ResponseEntity<Map<String,String>> methodArgsNotValidHandler(MethodArgumentNotValidException ex){
        Map<String,String> errorMap =new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach(error->{
            String fieldName= ((FieldError)error).getField();
            String msg = error.getDefaultMessage();
            errorMap.put(fieldName,msg);
        });
        return new ResponseEntity<>(errorMap,HttpStatus.BAD_REQUEST);
    }
}
