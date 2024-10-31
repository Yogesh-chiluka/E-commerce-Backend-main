package com.backend.Ecommerce.Backend.dto;

import java.util.*;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class CartDto {
    private long cartId;
    private Set<CartItemDto> items;
    private BigDecimal totalAmount;
}
