package com.quickcommerce.controller;

import com.quickcommerce.dto.ResponseDto;
import com.quickcommerce.entity.Product;
import com.quickcommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ResponseDto> getProducts() {
        List<Product> products = productService.getProducts();
        Map<String, Object> data = new HashMap<>();
        data.put("products", products);
        ResponseDto responseDto = new ResponseDto<>("Products retrieved successfully.", data);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ResponseDto> getProductById(@PathVariable Integer productId) {
        Product product = productService.getProduct(productId);
        Map<String, Object> data = new HashMap<>();
        data.put("product", product);
        ResponseDto responseDto = new ResponseDto<>("Products retrieved successfully.", data);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    public ResponseEntity addProduct(@RequestBody Product product) {
        Product addedProduct = productService.addProduct(product);

        return ResponseEntity.ok(addedProduct);
    }
}
