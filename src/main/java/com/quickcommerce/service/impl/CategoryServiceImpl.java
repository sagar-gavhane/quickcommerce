package com.quickcommerce.service.impl;

import com.quickcommerce.dto.CategoryDto;
import com.quickcommerce.dto.SubCategoryDto;
import com.quickcommerce.entity.Category;
import com.quickcommerce.entity.SubCategory;
import com.quickcommerce.repository.CategoryRepository;
import com.quickcommerce.repository.SubCategoryRepository;
import com.quickcommerce.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<Category> getCategories() {
        List<Category> categories = categoryRepository.findByIsActive(true);
        return categories;
    }

    @Override
    public List<CategoryDto> getFeaturedCategories() {
        List<Category> categories = categoryRepository.findByIsFeatured(true);
        List<CategoryDto> categoryDtos = categories.stream().map(category -> {
            if (category.getProduct() == null) {
                return modelMapper.map(category, CategoryDto.class);
            }

            CategoryDto cd = modelMapper.map(category, CategoryDto.class);

            return cd;
        }).collect(Collectors.toList());
        return categoryDtos;
    }

    @Override
    public List<SubCategoryDto> getSubCategoriesByCategoryId(Integer categoryId) {
        List<SubCategory> subCategories = subCategoryRepository.findByCategoryId(categoryId);
        List<SubCategoryDto> subCategoryDtos = subCategories.stream().map(subCategory -> {
            return modelMapper.map(subCategory, SubCategoryDto.class);
        }).collect(Collectors.toList());

        return subCategoryDtos;
    }

    @Override
    public CategoryDto getCategoryById(Integer categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(
                () -> new IllegalArgumentException("Category not found with id: " + categoryId)
        );
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);

        return categoryDto;
    }
}
