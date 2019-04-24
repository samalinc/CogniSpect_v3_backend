package com.bsuir.cognispect.controller.advice;

import com.bsuir.cognispect.exception.UniqueException;
import com.bsuir.cognispect.util.error.ApiError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            UniqueException.class,
            IllegalArgumentException.class,
            BadCredentialsException.class})
    public ResponseEntity<?> handleBadRequest(RuntimeException ex) {
        ApiError apiError = new ApiError(
                HttpStatus.BAD_REQUEST, ex.getMessage(), ex);

        return buildResponseEntity(apiError);
    }

    private ResponseEntity<Object> buildResponseEntity(ApiError apiError) {
        return new ResponseEntity<>(apiError, apiError.getStatus());
    }
}