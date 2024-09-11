package com.quickcommerce.dto;

import com.quickcommerce.entity.Attribute;
import com.quickcommerce.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VariantDto {
    private Integer id;
    private String name;
    private Float price;
    private Float discount;
    private List<Attribute> attributes;
    private Product product;
    private Instant createdAt;
    private Instant updatedAt;
}
