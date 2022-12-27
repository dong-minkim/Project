package com.dutmdcjf.backendserver.exception;

import com.dutmdcjf.backendserver.exception.util.ErrorCode;

public class AuthInterceptorException extends RuntimeException {
    private ErrorCode errorCode;

    public AuthInterceptorException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
