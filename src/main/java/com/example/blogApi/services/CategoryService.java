package com.example.blogApi.services;

import com.example.blogApi.payloads.CategoryDto;
import com.example.blogApi.payloads.UserDto;

import java.util.List;

public interface CategoryService {
    CategoryDto createCategory(CategoryDto categoryDto);
    CategoryDto updateCategory(CategoryDto categoryDto,Integer id);
    Void deleteCategory(Integer id);
    CategoryDto getCategoryById(Integer id);
    List<CategoryDto> getCategories();


}
