package com.challenge.tenpo.controller;

import com.challenge.tenpo.model.ErrorMessage;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerExceptionHandler {

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> resourceNotFoundException(Exception ex, WebRequest request) {
        ErrorMessage message = new ErrorMessage();
        message.setMessage(ex.getMessage());
        message.setStatusCode(HttpStatus.BAD_REQUEST.value());
        message.setTimestamp(LocalDateTime.now());
        return ResponseEntity.badRequest().body(message);
    }
}
