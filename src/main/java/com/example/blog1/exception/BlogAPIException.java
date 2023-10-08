package com.example.blog1.exception;

import org.springframework.http.HttpStatus;

public class BlogAPIException extends RuntimeException {

    private final HttpStatus status;

    public BlogAPIException(HttpStatus status, String message) {
        super(message);
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }
}

