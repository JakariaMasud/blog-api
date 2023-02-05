package com.example.blogApi.repositories;

import com.example.blogApi.entity.Category;
import com.example.blogApi.entity.Post;
import com.example.blogApi.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post,Integer> {

     List<Post> findByCategory(Category category);
     List<Post> findByUser(User user);
     List<Post> findByTitleContaining(String searchTerm);

}
