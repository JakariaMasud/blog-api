package com.example.blogApi.controllers;

import com.example.blogApi.payloads.ApiResponse;
import com.example.blogApi.payloads.PostDto;
import com.example.blogApi.services.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;
    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId){
        PostDto newPost = postService.createPost(postDto,categoryId,userId);
        return new  ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId){
        List<PostDto> postDtoList=postService.getPostByUser(userId);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }
    @GetMapping("/category/{categoryId}/posts")
    ResponseEntity<List<PostDto>>getPostsByCategory(@PathVariable Integer categoryId){
        List<PostDto> postDtoList = postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }
    @GetMapping("/posts")
    ResponseEntity<List<PostDto>> getAllPosts(
            @RequestParam(value = "pageNumber",required = false,defaultValue = "1") Integer pageNumber,
            @RequestParam(value = "pageSize" ,required = false,defaultValue = "3") Integer pageSize)
    {
        List<PostDto> postDtoList = postService.getAllPosts(pageNumber,pageSize);
        return new ResponseEntity<>(postDtoList,HttpStatus.OK);
    }
    @DeleteMapping("/posts/{postId}")
    ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId){
        postService.deletePost(postId);
        ApiResponse apiResponse = new ApiResponse("Successfully deleted",true);
        return new ResponseEntity<>(apiResponse,HttpStatus.OK);
    }
    @PutMapping("/posts/{postId}")
    ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto,@PathVariable Integer postId){
        PostDto updatedPost = postService.updatePost(postDto,postId);
        return new ResponseEntity<>(updatedPost,HttpStatus.OK);
    }
    @GetMapping("/posts/{postId}")
    ResponseEntity<PostDto> getPostById(@PathVariable Integer postId){
        PostDto postDto = postService.getPostById(postId);
        return new ResponseEntity<>(postDto,HttpStatus.OK);
        }



}
