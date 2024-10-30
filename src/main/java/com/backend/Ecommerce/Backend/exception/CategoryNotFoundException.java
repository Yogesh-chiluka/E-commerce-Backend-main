package com.backend.Ecommerce.Backend.exception;

public class CategoryNotFoundException extends RuntimeException{
    
    public CategoryNotFoundException(String message){
        super(message);
    }
}
