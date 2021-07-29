package com.crud.task.exception;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.crud.task.model.ErrorResponse;

@ControllerAdvice
public class GlobalExceptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {InvalidJWTException.class,
                               TaskNotFoundException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST) // 400
    @ResponseBody
    public ErrorResponse handleBadRequest(HttpServletRequest req, Throwable exception) {
        return ErrorResponse.builder().errorMsg(exception.getMessage()).status(HttpStatus.BAD_REQUEST).build();
    }

}
