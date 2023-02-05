package com.example.blogApi.payloads;

import com.example.blogApi.entity.Post;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentDto {
    Integer id;
    String content;
    Post post;
}
