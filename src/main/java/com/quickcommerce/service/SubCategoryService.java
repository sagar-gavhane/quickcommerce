package com.quickcommerce.service;

import com.quickcommerce.dto.SubCategoryDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface SubCategoryService {
    List<SubCategoryDto> getSubCategories();

    SubCategoryDto getSubCategoryById(Integer subCategoryId);
}
