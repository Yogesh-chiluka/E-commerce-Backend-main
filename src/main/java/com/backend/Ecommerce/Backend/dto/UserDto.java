package com.backend.Ecommerce.Backend.dto;


import com.backend.Ecommerce.Backend.model.Order;

import java.util.*;


import lombok.Data;

@Data
public class UserDto {
    private Long Id;
    private String firstName;
    private String lastName;
    private String email;
    private List<Order> order;
    private CartDto cart;
}
