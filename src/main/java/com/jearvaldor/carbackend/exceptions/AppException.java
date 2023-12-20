package com.jearvaldor.carbackend.exceptions;

import org.springframework.http.HttpStatus;

public class AppException extends RuntimeException{
    private final HttpStatus status;

    public AppException(String messagge, HttpStatus status){
        super(messagge);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}
