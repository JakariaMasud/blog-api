package com.example.blogApi.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer categoryId;
    @Column(name = "title", nullable = false, length = 100)
    private String categoryTitle;
    private String categoryDesc;
    @OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
    private List<Post> posts;
}
