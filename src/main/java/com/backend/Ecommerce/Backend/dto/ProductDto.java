package com.backend.Ecommerce.Backend.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import com.backend.Ecommerce.Backend.model.Category;

@Data
public class ProductDto {
    private Long id;
    private String name;
    private String brand;
    private BigDecimal price;
    private int inventory;
    private String description;
    private Category category;

    private List<ImageDto> images;
}