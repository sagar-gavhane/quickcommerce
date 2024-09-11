package com.quickcommerce.service;

import com.quickcommerce.dto.ProductDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    ProductDto addProduct(ProductDto product);

    List<ProductDto> getProducts();

    ProductDto getProduct(Integer productId);

    String getProductSlug(String productName);
}
