package com.dutmdcjf.authserver.exception;

import com.dutmdcjf.authserver.exception.util.ErrorCode;

public class UserLoginException extends RuntimeException {
    private ErrorCode errorCode;

    public UserLoginException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
}
