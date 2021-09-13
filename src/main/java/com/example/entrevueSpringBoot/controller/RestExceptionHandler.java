package com.example.entrevueSpringBoot.controller;

import com.example.entrevueSpringBoot.exception.ServiceException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ServiceException.class)
    protected ResponseEntity<Object> handleError(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                "Une erreur interne est survenue",
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request);
    }
}
