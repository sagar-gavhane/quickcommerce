package com.quickcommerce.service.impl;

import com.quickcommerce.dto.SubCategoryDto;
import com.quickcommerce.entity.SubCategory;
import com.quickcommerce.repository.SubCategoryRepository;
import com.quickcommerce.service.SubCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class SubCategoryServiceImpl implements SubCategoryService {
    @Autowired
    private SubCategoryRepository subCategoryRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<SubCategoryDto> getSubCategories() {
        List<SubCategory> subCategories = subCategoryRepository.findByIsActive(true);
        List<SubCategoryDto> subCategoryDtos = subCategories
                .stream()
                .map(subCategory -> modelMapper.map(subCategory, SubCategoryDto.class))
                .collect(Collectors.toList());

        return subCategoryDtos;
    }

    @Override
    public SubCategoryDto getSubCategoryById(Integer subCategoryId) {
        SubCategory subCategory = subCategoryRepository
                .findById(subCategoryId)
                .orElseThrow(() -> new IllegalArgumentException("No sub-category exists with id " + subCategoryId));
        SubCategoryDto subCategoryDto = modelMapper.map(subCategory, SubCategoryDto.class);

        return subCategoryDto;
    }
}
