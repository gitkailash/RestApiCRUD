package com.treeleaf.restapi.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;
@Entity
@Table(name= "blog_post")
@Data
public class BlogPost {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne
    @JoinColumn(name = "thumbnail_image_id")
    private ThumbNailImage thumbnailImage;

    @OneToMany(mappedBy = "blogPost")
    private List<Comment> comments;
}
