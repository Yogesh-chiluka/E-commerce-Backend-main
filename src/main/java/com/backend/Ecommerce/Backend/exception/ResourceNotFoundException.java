package com.backend.Ecommerce.Backend.exception;

public class ResourceNotFoundException extends RuntimeException {
    
    public ResourceNotFoundException(String message){
        super(message);
    }
}
