package com.quickcommerce.controller;

import com.quickcommerce.dto.ProductDto;
import com.quickcommerce.dto.ResponseDto;
import com.quickcommerce.service.ProductService;
import jakarta.annotation.security.RolesAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/product")
public class ProductController {
    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<ResponseDto<Map<String, Object>>> getProducts(
            @RequestParam(value = "categoryId", required = false) Optional<Integer> categoryId,
            @RequestParam(value = "page", defaultValue = "0", required = false) Optional<Integer> page
    ) {
        List<ProductDto> products = productService.getProducts(categoryId, page);
        Map<String, Object> data = new HashMap<>();
        data.put("products", products);
        ResponseDto responseDto = new ResponseDto<>("Products retrieved successfully.", data);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ResponseDto> getProductById(@PathVariable Integer productId) {
        ProductDto product = productService.getProduct(productId);
        Map<String, Object> data = new HashMap<>();
        data.put("product", product);
        ResponseDto responseDto = new ResponseDto<>("Products retrieved successfully.", data);
        return ResponseEntity.ok(responseDto);
    }

    @PostMapping
    @RolesAllowed({"admin", "product_manager"})
    // TODO: only product_manager, admin have access to call this endpoint
    public ResponseEntity<ResponseDto<Map<String, Object>>> addProduct(@RequestBody ProductDto product) {
        ProductDto addedProduct = productService.addProduct(product);

        Map<String, Object> data = new HashMap<>();
        data.put("product", addedProduct);
        ResponseDto responseDto = new ResponseDto<>("Products added successfully.", data);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PatchMapping("/{productId}")
    // TODO: only product_manager, admin have access to call this endpoint
    public void updateProduct(@PathVariable("productId") Integer productId, @RequestBody ProductDto productDto) {
        System.out.println("PATCH /api/product API called with product id " + productId + " and product data " + productDto);
        return;
    }

    @DeleteMapping("/{productId}")
    // TODO: only product_manager, admin have access to call this endpoint
    public void deleteProduct(@PathVariable("productId") Integer productId) {
        System.out.println("DELETE /api/product API called with product id " + productId);
        return;
    }
}
