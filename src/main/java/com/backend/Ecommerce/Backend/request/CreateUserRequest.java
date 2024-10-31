package com.backend.Ecommerce.Backend.request;

import lombok.Data;

@Data
public class CreateUserRequest {
        
    private String firstName;
    private String lastName;
    private String email;
    private String password;
}
