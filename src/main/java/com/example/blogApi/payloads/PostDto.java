package com.example.blogApi.payloads;

import com.example.blogApi.entity.Category;
import com.example.blogApi.entity.User;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostDto {
    private String postId;
    private String title;
    private String content;
    private String image;
    private Date addedDate;
    private CategoryDto category;
    private UserDto user;
}
