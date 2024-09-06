package com.quickcommerce.dto;

import com.quickcommerce.entity.Brand;
import com.quickcommerce.entity.Image;
import com.quickcommerce.entity.Inventory;
import com.quickcommerce.entity.Variant;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductDto {
    private Integer id;
    private String name;
    private String description;
    private Integer sku;
    private boolean isActive = false;
    private boolean isFeatured = false;
    private String offer;
    private String slug;
    private Inventory inventory;
    private CategoryDto category;
    private Brand brand;
    private List<Image> images;
    private List<Variant> variants;
    private Instant createdAt;
    private Instant updatedAt;
}