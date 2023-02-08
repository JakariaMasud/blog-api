package com.example.blogApi.controllers;

import com.example.blogApi.payloads.ApiResponse;
import com.example.blogApi.payloads.CommentDto;
import com.example.blogApi.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/api")
public class CommentController {
    @Autowired
    CommentService commentService;

    @PostMapping("/posts/{postId}/comments")
    ResponseEntity<CommentDto> addComment(@PathVariable("postId") Integer postId,
                                          @RequestBody CommentDto commentDto) {
        CommentDto savedComment = commentService.addComment(commentDto, postId);
        return new ResponseEntity<>(savedComment, HttpStatus.CREATED);
    }

    @DeleteMapping("/comments/{commentId}")
    ResponseEntity<ApiResponse> deleteComment(@PathVariable Integer commentId) {
        commentService.deleteComment(commentId);
        ApiResponse apiResponse = new ApiResponse("successfully deleted", true);
        return new ResponseEntity<>(apiResponse, HttpStatus.OK);
    }

}
