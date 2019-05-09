package com.bsuir.cognispect.exception;

public class UniqueException extends RuntimeException {
    public UniqueException(String message) {
        super(message);
    }

    public UniqueException(String objectName, String propertyName, String propertyValue) {
        super(objectName + " with " + propertyName + ": " + propertyValue + " already exist");
    }
}
