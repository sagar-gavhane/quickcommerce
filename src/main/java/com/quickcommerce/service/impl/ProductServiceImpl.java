package com.quickcommerce.service.impl;

import com.quickcommerce.entity.Product;
import com.quickcommerce.repository.ProductRepository;
import com.quickcommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Override
    public List<Product> getProducts() {
        List<Product> products = productRepository.findByIsActive(true);
        return products;
    }

    @Override
    public Product getProduct(Integer productId) {
        Product product = productRepository
                .findById(productId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid product id (" + productId + ") passed"));

        return product;
    }

    @Override
    public Product addProduct(Product product) {
        // TODO: use product dto
        // TODO: validate product dto before creating product
        Product addedProduct = productRepository.save(product);
        return addedProduct;
    }
}
