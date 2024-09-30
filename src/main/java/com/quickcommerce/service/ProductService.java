package com.quickcommerce.service;

import com.quickcommerce.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface ProductService {
    ProductDto addProduct(ProductDto product);

    List<ProductDto> getProducts(Optional<Integer> categoryId, Optional<Integer> page);

    ProductDto getProduct(Integer productId);

    String getProductSlug(String productName);
}
