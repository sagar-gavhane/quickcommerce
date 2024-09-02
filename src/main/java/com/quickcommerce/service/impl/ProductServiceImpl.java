package com.quickcommerce.service.impl;

import com.quickcommerce.entity.Product;
import com.quickcommerce.repository.ProductRepository;
import com.quickcommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        Product addedProduct = productRepository.save(product);
        return addedProduct;
    }
}
