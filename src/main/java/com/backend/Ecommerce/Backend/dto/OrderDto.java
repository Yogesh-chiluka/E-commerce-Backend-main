package com.backend.Ecommerce.Backend.dto;

import lombok.Data;
import java.math.BigDecimal;
import java.util.*;
import java.time.*;

@Data
public class OrderDto {
    
    private Long id;
    private Long userId;
    private LocalDateTime OrderDate;
    private BigDecimal totalAmount;
    private String status;
    private List<OrderItemDto> items;
}
