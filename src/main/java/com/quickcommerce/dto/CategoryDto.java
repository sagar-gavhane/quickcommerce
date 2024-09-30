package com.quickcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CategoryDto {
    private Integer id;
    private String name;
    private boolean isActive = true;
    private boolean isFeatured = false;
    private String thumbnail;
    //    private List<ProductDto> product;
    private Instant createdAt;
    private Instant updatedAt;
}