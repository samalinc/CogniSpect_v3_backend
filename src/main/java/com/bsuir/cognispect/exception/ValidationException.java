package com.bsuir.cognispect.exception;

import com.bsuir.cognispect.util.error.ApiSubError;
import lombok.Getter;

import java.util.List;

@Getter
public class ValidationException extends RuntimeException {
    private List<ApiSubError> apiSubErrors;

    public ValidationException(List<ApiSubError> apiSubErrors) {
        this.apiSubErrors = apiSubErrors;
    }
}
