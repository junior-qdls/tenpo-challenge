package com.challenge.tenpo.helper;

import org.springframework.http.HttpStatus;

public class TenpoChallengeException extends RuntimeException {
    private HttpStatus status;
    private String message;

    public TenpoChallengeException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public TenpoChallengeException(String message) {
        this.message = message;
    }

    public TenpoChallengeException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
