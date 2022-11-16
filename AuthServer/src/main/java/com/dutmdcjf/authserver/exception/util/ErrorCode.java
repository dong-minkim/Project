package com.dutmdcjf.authserver.exception.util;

public enum ErrorCode {
    LOGIN_BAD_REQUEST(401, null, "PLEASE LOGIN AGAIN"),
    DO_NOT_MATCHING_TOKEN(403, null, "REFRESH TOKEN IS NOT SAME"),
    NOT_FOUND_USER(404, null, "NOT FOUND USER"),
    JWT_EXPIRED(405, null, "TOKEN HAS EXPIRED");

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
