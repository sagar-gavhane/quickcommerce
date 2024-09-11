package com.quickcommerce.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ImageDto {
    private Integer id;
    private String source;
    private String altName;
    private Instant createdAt;
    private Instant updatedAt;
}
