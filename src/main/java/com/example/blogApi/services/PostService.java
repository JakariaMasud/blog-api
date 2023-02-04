package com.example.blogApi.services;

import com.example.blogApi.payloads.PostDto;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto,Integer categoryId,Integer userId);
    PostDto updatePost(PostDto postDto,Integer postId);
    Void deletePost(Integer postId);
    PostDto getPostById(Integer postId);
    List<PostDto> getAllPosts(Integer pageNumber,Integer pageSize);
    List<PostDto> getPostByUser(Integer userId);
    List<PostDto> getPostByCategory(Integer categoryId);
}
