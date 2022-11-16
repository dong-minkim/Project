package com.dutmdcjf.authserver.controller;

import com.dutmdcjf.authserver.exception.UserLoginException;
import com.dutmdcjf.authserver.exception.util.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@ControllerAdvice
public class LoginExceptionController {

    @ExceptionHandler(UserLoginException.class)
    protected ResponseEntity<ErrorResponse> notfoundUserException(UserLoginException e) {
        final ErrorResponse errorResponse
                = ErrorResponse
                .create()
                .status(HttpStatus.BAD_REQUEST.value())
                .code(e.getErrorCode().getStatus())
                .message(e.getErrorCode().getMessage());

        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(RuntimeException.class)
    protected ResponseEntity<ErrorResponse> notfoundUserException(RuntimeException e) {
        final ErrorResponse response
                = ErrorResponse
                .create()
                .status(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .code(500)
                .message("Internal Server Error");

        log.error("Critical Exception", e);

        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
