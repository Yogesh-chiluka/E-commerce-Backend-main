package com.backend.Ecommerce.Backend.dto;

import lombok.Data;
import java.math.BigDecimal;

@Data
public class OrderItemDto {
    
    private Long productId;
    private String productName;
    private String BrandName;
    private int quantity;
    private BigDecimal price;
}
