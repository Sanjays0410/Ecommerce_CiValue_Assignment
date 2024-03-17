package com.demo.ciValue.exception;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class ciValueException extends RuntimeException{

    public ciValueException(String message) {
        super(message);

    }


}
