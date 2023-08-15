package com.treeleaf.restapi.controller;

import com.treeleaf.restapi.entity.BlogPost;
import com.treeleaf.restapi.service.BlogPostService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/blogPosts")
public class BlogPostController {

    private final BlogPostService blogPostService;

    @GetMapping
    public List<BlogPost> getAllBlogPosts() {
        return blogPostService.getAllBlogPosts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<BlogPost> getBlogPostById(@PathVariable Long id) {
        BlogPost blogPost = blogPostService.getBlogPostById(id);
        if (blogPost != null) {
            return ResponseEntity.ok(blogPost);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<BlogPost> SaveBlogPost(@RequestBody BlogPost blogPost) {
        BlogPost createdBlogPost = blogPostService.saveBlogPost(blogPost);
        return ResponseEntity.ok(createdBlogPost);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BlogPost> updateBlogPost(@PathVariable Long id, @RequestBody BlogPost updatedBlogPost) {
        BlogPost updated = blogPostService.updateBlogPost(id, updatedBlogPost);
        if (updated != null) {
            return ResponseEntity.ok(updated);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBlogPost(@PathVariable Long id) {
        blogPostService.deleteBlogPost(id);
        return ResponseEntity.ok().build();
    }
}
