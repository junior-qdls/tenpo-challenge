package com.challenge.tenpo.model;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ErrorMessage {
    private int statusCode;
    private LocalDateTime timestamp;
    private String message;

}
