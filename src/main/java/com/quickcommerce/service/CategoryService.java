package com.quickcommerce.service;

import com.quickcommerce.dto.CategoryDto;
import com.quickcommerce.dto.SubCategoryDto;
import com.quickcommerce.entity.Category;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {
    List<Category> getCategories();

    List<CategoryDto> getFeaturedCategories();

    List<SubCategoryDto> getSubCategoriesByCategoryId(Integer categoryId);

    CategoryDto getCategoryById(Integer categoryId);
}
