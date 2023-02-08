package com.example.blogApi.services;

import com.example.blogApi.entity.Category;
import com.example.blogApi.exceptions.ResourceNotFoundException;
import com.example.blogApi.payloads.CategoryDto;
import com.example.blogApi.repositories.CategoryRepository;
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
    public CategoryDto createCategory(CategoryDto categoryDto) {
        Category category = dtoToCategory(categoryDto);
        Category savedCategory = categoryRepository.save(category);
        return categoryToDto(savedCategory);
    }

    @Override
    public CategoryDto updateCategory(CategoryDto categoryDto, Integer id) {
        Category selectedCategory = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", id));
        selectedCategory.setCategoryTitle(categoryDto.getCategoryTitle());
        selectedCategory.setCategoryDesc(categoryDto.getCategoryDesc());
        Category category = categoryRepository.save(selectedCategory);
        return categoryToDto(category);
    }

    @Override
    public Void deleteCategory(Integer id) {
        Category selectedCategory = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", id));
        categoryRepository.delete(selectedCategory);
        return null;
    }

    @Override
    public CategoryDto getCategoryById(Integer id) {
        Category selectedCategory = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "CategoryId", id));
        return categoryToDto(selectedCategory);
    }

    @Override
    public List<CategoryDto> getCategories() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> categoryDtoList = categories.stream().map(this::categoryToDto).collect(Collectors.toList());
        return categoryDtoList;
    }

    CategoryDto categoryToDto(Category category) {
        CategoryDto categoryDto = modelMapper.map(category, CategoryDto.class);
        return categoryDto;
    }

    Category dtoToCategory(CategoryDto categoryDto) {
        Category category = modelMapper.map(categoryDto, Category.class);
        return category;
    }
}
