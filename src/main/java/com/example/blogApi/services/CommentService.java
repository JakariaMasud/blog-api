package com.example.blogApi.services;

import com.example.blogApi.payloads.CommentDto;
import org.springframework.stereotype.Service;


public interface CommentService {
    CommentDto addComment(CommentDto commentDto,Integer postId);
    void deleteComment(Integer commentId);
}
