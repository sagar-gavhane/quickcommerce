package com.quickcommerce.service;

import com.quickcommerce.entity.Product;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {
    Product addProduct(Product product);
}
