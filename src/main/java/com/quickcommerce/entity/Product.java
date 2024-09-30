package com.quickcommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.Instant;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(nullable = true, unique = true)
    private Integer sku;

    @Column
    private boolean isActive = false;

    @Column
    private boolean isFeatured = false;

    @Column(nullable = false)
    private Float offer = 0.0f;

    @Column(nullable = false)
    private float marketPrice;

    @Column(nullable = false)
    private float salePrice;

    @Column(nullable = false, unique = true)
    private String slug;

//    @OneToOne
//    @JoinColumn(name = "inventory_id")
//    private Inventory inventory;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = false)
    private List<Image> images;

    @OneToMany(mappedBy = "product", cascade = CascadeType.PERSIST, orphanRemoval = false)
    private List<Variant> variants;

    @CreatedDate
    private Instant createdAt;

    @LastModifiedDate
    private Instant updatedAt;
}
