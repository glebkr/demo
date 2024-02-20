package com.example.demo.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public HashMap<String, String> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        HashMap<String, String> hashMap = new HashMap<>();
        ex.getBindingResult().getFieldErrors()
                .forEach(error ->
                        hashMap.put(error.getField(), error.getDefaultMessage()));
        return hashMap;
    }

}
