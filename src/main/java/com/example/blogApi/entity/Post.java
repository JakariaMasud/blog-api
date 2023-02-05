package com.example.blogApi.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postId;
    @Column(nullable = false)
    private String title;
    @Column(length = 1000)
    private String content;
    private String image;
    private Date addedDate;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private  Category category;
    @ManyToOne(cascade = CascadeType.ALL)
    @JsonIgnore
    private User user;
    @OneToMany(mappedBy = "post",cascade = CascadeType.ALL)
    private List<Comment> comments;
}
