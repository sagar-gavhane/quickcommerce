package com.quickcommerce.service;

import com.quickcommerce.entity.Product;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductService {
    Product addProduct(Product product);

    List<Product> getProducts();

    Product getProduct(Integer productId);
}
