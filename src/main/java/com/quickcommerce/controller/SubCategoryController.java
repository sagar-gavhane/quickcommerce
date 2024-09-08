package com.quickcommerce.controller;

import com.quickcommerce.dto.ResponseDto;
import com.quickcommerce.dto.SubCategoryDto;
import com.quickcommerce.service.SubCategoryService;
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
@RequestMapping("/api/sub-category")
public class SubCategoryController {
    @Autowired
    private SubCategoryService subCategoryService;

    @GetMapping
    public ResponseEntity<ResponseDto<List<SubCategoryDto>>> getSubCategories() {
        List<SubCategoryDto> subCategories = subCategoryService.getSubCategories();

        Map<String, Object> data = new HashMap<>();
        data.put("subCategories", subCategories);
        ResponseDto responseDto = new ResponseDto<>("Sub-categories retrieved successfully.", data);

        return ResponseEntity.ok(responseDto);
    }

    @GetMapping("/{subCategoryId}")
    public ResponseEntity<ResponseDto<List<SubCategoryDto>>> getSubCategoryById(@PathVariable("subCategoryId") Integer subCategoryId) {
        SubCategoryDto subCategory = subCategoryService.getSubCategoryById(subCategoryId);

        Map<String, Object> data = new HashMap<>();
        data.put("subCategory", subCategory);
        ResponseDto responseDto = new ResponseDto<>("Sub-categories retrieved successfully.", data);

        return ResponseEntity.ok(responseDto);
    }
}
