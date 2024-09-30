package com.quickcommerce.controller;

import com.quickcommerce.dto.CategoryDto;
import com.quickcommerce.dto.ResponseDto;
import com.quickcommerce.dto.SubCategoryDto;
import com.quickcommerce.entity.Category;
import com.quickcommerce.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @GetMapping
    public ResponseEntity<ResponseDto> getCategories() {
        List<Category> categories = categoryService.getCategories();
        Map<String, Object> data = new HashMap<>();
        data.put("categories", categories);
        ResponseDto responseDto = new ResponseDto<>("Categories retrieved successfully.", data);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{categoryId}")
    public ResponseEntity<ResponseDto> getCategoryById(@PathVariable("categoryId") Integer categoryId) {
        CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
        Map<String, Object> data = new HashMap<>();
        data.put("category", categoryDto);
        ResponseDto responseDto = new ResponseDto<>("Category retrieved successfully.", data);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/featured")
    public ResponseEntity<ResponseDto> getFeaturedCategories() {
        List<CategoryDto> categories = categoryService.getFeaturedCategories();
        Map<String, Object> data = new HashMap<>();
        data.put("categories", categories);
        ResponseDto responseDto = new ResponseDto<>("Categories retrieved successfully.", data);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{categoryId}/sub-categories")
    public ResponseEntity<ResponseDto> getSubCategoriesByCategoryId(@PathVariable("categoryId") Integer categoryId) {
        List<SubCategoryDto> subCategoryDtos = categoryService.getSubCategoriesByCategoryId(categoryId);
        Map<String, Object> data = new HashMap<>();
        data.put("subCategories", subCategoryDtos);
        ResponseDto responseDto = new ResponseDto<>("Sub categories retrieved successfully.", data);
        return ResponseEntity.ok(responseDto);
    }
}
