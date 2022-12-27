package com.dutmdcjf.backendserver.exception.util;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;

public class ErrorResponse {
    private LocalDateTime timestamp = LocalDateTime.now();

    private int status;
    @JsonProperty("message")
    private String message;
    @JsonProperty("code")
    private int code;

    public ErrorResponse() {
    }

    static public ErrorResponse create() {
        return new ErrorResponse();
    }

    public ErrorResponse status(int status) {
        this.status = status;
        return this;
    }

    public ErrorResponse code(int code) {
        this.code = code;
        return this;
    }

    public ErrorResponse message(String message) {
        this.message = message;
        return this;
    }
}
