package com.quickcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class BrandDto {
    private Integer id;
    private String name;
    private String thumbnail;
    private Instant createdAt;
    private Instant updatedAt;
}
