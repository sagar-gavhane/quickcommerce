package com.quickcommerce.service.impl;

import com.quickcommerce.dto.CategoryDto;
import com.quickcommerce.dto.ProductDto;
import com.quickcommerce.entity.Category;
import com.quickcommerce.repository.CategoryRepository;
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

            ProductDto productDto = modelMapper.map(category.getProduct(), ProductDto.class);

            CategoryDto cd = modelMapper.map(category, CategoryDto.class);
            cd.setProduct(productDto);

            return cd;
        }).collect(Collectors.toList());
        return categoryDtos;
    }
}
