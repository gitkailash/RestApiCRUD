package com.treeleaf.restapi.entity;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "thumb_nail_image")
@Data
public class ThumbNailImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String imageUrl;

    @OneToOne(mappedBy = "thumbnailImage")
    private BlogPost blogPost;
}
