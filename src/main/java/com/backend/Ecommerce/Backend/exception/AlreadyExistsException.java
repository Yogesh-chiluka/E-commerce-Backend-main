package com.backend.Ecommerce.Backend.exception;

public class AlreadyExistsException extends RuntimeException{
    
    public AlreadyExistsException(String message){
        super(message);
    }

}
