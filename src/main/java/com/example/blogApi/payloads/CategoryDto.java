package com.example.blogApi.payloads;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Integer categoryId;

    private String categoryTitle;
    private String categoryDesc;
}
