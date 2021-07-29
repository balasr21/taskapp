package com.crud.task.exception;

import java.io.Serializable;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "user not found")
public class TaskNotFoundException extends RuntimeException implements Serializable {

    private static final long serialVersionUID = 5529704323199635206L;

    public TaskNotFoundException(String message){
        super(message);
    }

}
