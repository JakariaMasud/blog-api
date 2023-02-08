package com.example.blogApi.controllers;

import com.example.blogApi.config.AppConstants;
import com.example.blogApi.payloads.ApiResponse;
import com.example.blogApi.payloads.PostDto;
import com.example.blogApi.payloads.PostResponse;
import com.example.blogApi.services.FileService;
import com.example.blogApi.services.PostService;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

@Controller
@RequestMapping("/api")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private FileService fileService;
    @Value("${project.image}")
    private String path;

    @PostMapping("/user/{userId}/category/{categoryId}/posts")
    ResponseEntity<PostDto> createPost(@RequestBody PostDto postDto, @PathVariable Integer userId, @PathVariable Integer categoryId) {
        PostDto newPost = postService.createPost(postDto, categoryId, userId);
        return new ResponseEntity<>(newPost, HttpStatus.CREATED);
    }

    @GetMapping("/user/{userId}/posts")
    ResponseEntity<List<PostDto>> getPostsByUser(@PathVariable Integer userId) {
        List<PostDto> postDtoList = postService.getPostByUser(userId);
        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }

    @GetMapping("/category/{categoryId}/posts")
    ResponseEntity<List<PostDto>> getPostsByCategory(@PathVariable Integer categoryId) {
        List<PostDto> postDtoList = postService.getPostByCategory(categoryId);
        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }

    @GetMapping("/posts")
    ResponseEntity<PostResponse> getAllPosts(@RequestParam(value = "pageNumber", required = false, defaultValue = AppConstants.PAGE_NUMBER) Integer pageNumber, @RequestParam(value = "pageSize", required = false, defaultValue = AppConstants.PAGE_SIZE) Integer pageSize, @RequestParam(value = "sortBy", required = false, defaultValue = AppConstants.SORT_BY) String sortBy, @RequestParam(value = "sortDir", required = false, defaultValue = AppConstants.SORT_DIR) String sortDir) {
        PostResponse postResponse = postService.getAllPosts(pageNumber, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(postResponse, HttpStatus.OK);
    }

    @DeleteMapping("/posts/{postId}")
    ResponseEntity<ApiResponse> deletePost(@PathVariable Integer postId) {
        postService.deletePost(postId);
        ApiResponse apiResponse = new ApiResponse("Successfully deleted", true);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

    @PutMapping("/posts/{postId}")
    ResponseEntity<PostDto> updatePost(@RequestBody PostDto postDto, @PathVariable Integer postId) {
        PostDto updatedPost = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @GetMapping("/posts/{postId}")
    ResponseEntity<PostDto> getPostById(@PathVariable Integer postId) {
        PostDto postDto = postService.getPostById(postId);
        return new ResponseEntity<>(postDto, HttpStatus.OK);
    }

    @GetMapping("/posts/search/{keyword}")
    ResponseEntity<List<PostDto>> searchPosts(@PathVariable("keyword") String keyword) {
        List<PostDto> postDtoList = postService.SearchPosts(keyword);
        return new ResponseEntity<>(postDtoList, HttpStatus.OK);
    }

    @PostMapping("/posts/image/upload/{postId}")
    ResponseEntity<PostDto> uploadPostImage(@RequestParam("image") MultipartFile imageFile, @PathVariable("postId") Integer postId) throws IOException {
        PostDto postDto = postService.getPostById(postId);
        String fileName = fileService.uploadImage(path, imageFile);
        postDto.setImage(fileName);
        PostDto updatedPost = postService.updatePost(postDto, postId);
        return new ResponseEntity<>(updatedPost, HttpStatus.OK);
    }

    @GetMapping(value = "posts/image/{imageName}", produces = MediaType.IMAGE_JPEG_VALUE)
    public void downloadImage(@PathVariable("imageName") String imageName, HttpServletResponse response) throws IOException {
        InputStream inputStream = fileService.getResource(path, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(inputStream, response.getOutputStream());
    }

}
