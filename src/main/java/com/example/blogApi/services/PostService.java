package com.example.blogApi.services;

import com.example.blogApi.payloads.PostDto;
import com.example.blogApi.payloads.PostResponse;

import java.util.List;

public interface PostService {
    PostDto createPost(PostDto postDto,Integer categoryId,Integer userId);
    PostDto updatePost(PostDto postDto,Integer postId);
    Void deletePost(Integer postId);
    PostDto getPostById(Integer postId);
    PostResponse getAllPosts(Integer pageNumber, Integer pageSize,String sortBy,String sortDir);
    List<PostDto> getPostByUser(Integer userId);
    List<PostDto> getPostByCategory(Integer categoryId);
    List<PostDto> SearchPosts(String searchTerm);
}
