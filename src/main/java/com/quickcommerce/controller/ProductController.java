package com.quickcommerce.controller;

import com.quickcommerce.entity.Product;
import com.quickcommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @PostMapping
    private ResponseEntity addProduct(@RequestBody Product product) {
        Product addedProduct = productService.addProduct(product);

        return ResponseEntity.ok(addedProduct);
    }
}
