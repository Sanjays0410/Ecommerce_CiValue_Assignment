package com.demo.ciValue.exception;

import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class RestExceptionHandler {

    @ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
    @ExceptionHandler(value = ConstraintViolationException.class)
    public Map<String,String> limit(ConstraintViolationException ex)
    {
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("Error Description",ex.getMessage());
        errorMap.put("Error Code", "406");
        return errorMap;

    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(value = ciValueException.class)
    public Map<String,String> exception(ciValueException ex)
    {
        Map<String,String> errorMap=new HashMap<>();
        errorMap.put("Error Code", "404");
        errorMap.put("Error Description",ex.getMessage());
        return errorMap;

    }
}
