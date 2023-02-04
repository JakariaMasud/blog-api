package com.example.blogApi.payloads;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CategoryDto {
    private Integer categoryId;
    @NotEmpty(message = "Category Title Can not be empty")
    @Size(min = 5,message = "minimum size should be at least 5 character")
    private String categoryTitle;
    @NotEmpty(message = "Description can not be empty")
    @Size(min = 10,message = "Description size must be at least 10 characters")
    private String categoryDesc;
}
