package com.dutmdcjf.backendserver.exception.util;

public enum ErrorCode {
    BAD_REQUEST(400, null, "BAD REQUEST"),
    JWT_EXPIRED(401, null, "TOKEN HAS EXPIRED"),
    JWT_NOT_EXIST(401, null, "TOKEN DOES NOT EXIST"),
    EXPIRE_ONE_MINUTE_LEFT(401, null, "THERE ARE 1 MINUTES LEFT TO EXPIRE");


    private final int status;
    private final String code;
    private final String message;

    ErrorCode(final int status, final String code, final String message) {
        this.status = status;
        this.message = message;
        this.code = code;
    }

    public int getStatus() {
        return status;
    }

    public String getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
