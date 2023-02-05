package com.example.blogApi.services;

import com.example.blogApi.entity.Comment;
import com.example.blogApi.entity.Post;
import com.example.blogApi.exceptions.ResourceNotFoundException;
import com.example.blogApi.payloads.CommentDto;
import com.example.blogApi.repositories.CommentRepository;
import com.example.blogApi.repositories.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService{
    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Override
    public CommentDto addComment(CommentDto commentDto, Integer postId) {
        Post post = postRepository.findById(postId).orElseThrow(()->new ResourceNotFoundException("Post","postId",postId));
        commentDto.setPost(post);
        Comment comment = modelMapper.map(commentDto, Comment.class);
        Comment savedComment = commentRepository.save(comment);
        return modelMapper.map(savedComment,CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment","commentId",commentId));
        commentRepository.delete(comment);

    }
}
