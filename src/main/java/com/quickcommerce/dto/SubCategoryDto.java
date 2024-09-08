package com.quickcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SubCategoryDto {
    private Integer id;
    private String name;
    private boolean isActive = true;
    private String thumbnail;
    private CategoryDto categoryDto;
    private Instant createdAt;
    private Instant updatedAt;
}
