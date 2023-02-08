package com.example.blogApi.controllers;

import com.example.blogApi.payloads.CategoryDto;
import com.example.blogApi.services.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categories")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @PostMapping("/")
    ResponseEntity<CategoryDto> createCategory(@Valid @RequestBody CategoryDto categoryDto) {
        CategoryDto createdCategoryDto = categoryService.createCategory(categoryDto);
        return new ResponseEntity<>(createdCategoryDto, HttpStatus.CREATED);
    }

    @PutMapping("/{categoryId}")
    ResponseEntity<CategoryDto> updateCategory(@Valid @RequestBody CategoryDto categoryDto, @PathVariable Integer categoryId) {
        CategoryDto updatedCategoryDto = categoryService.updateCategory(categoryDto, categoryId);
        return new ResponseEntity<>(updatedCategoryDto, HttpStatus.OK);
    }

    @DeleteMapping("/{categoryId}")
    ResponseEntity<Void> deleteCategory(@PathVariable("categoryId") Integer id) {
        categoryService.deleteCategory(id);
        return null;
    }

    @GetMapping("/{categoryId}")
    ResponseEntity<CategoryDto> getCategoryById(@PathVariable Integer categoryId) {
        CategoryDto categoryDto = categoryService.getCategoryById(categoryId);
        return new ResponseEntity<>(categoryDto, HttpStatus.FOUND);
    }

    @GetMapping("/")
    ResponseEntity<List<CategoryDto>> getAllCategory() {
        List<CategoryDto> categoryDtoList = categoryService.getCategories();
        return new ResponseEntity<>(categoryDtoList, HttpStatus.OK);
    }

}
