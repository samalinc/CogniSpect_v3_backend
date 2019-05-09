package com.bsuir.cognispect.exception;

import java.util.UUID;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(String resourceName, UUID resourceId) {
        super(resourceName + " with ID: " + resourceId + " not found");
    }
}



