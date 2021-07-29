package com.crud.task.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "user not found")
public class InvalidJWTException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = -7500019729015755782L;

    public InvalidJWTException(String message){
        super(message);
    }

}
