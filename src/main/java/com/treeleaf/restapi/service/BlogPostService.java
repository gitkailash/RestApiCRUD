package com.treeleaf.restapi.service;

import com.treeleaf.restapi.entity.BlogPost;

import java.util.List;

public interface BlogPostService {
    List<BlogPost> getAllBlogPosts();
    BlogPost getBlogPostById(Long id);
    BlogPost saveBlogPost(BlogPost blogPost);
    BlogPost updateBlogPost(Long id, BlogPost updatedBlogPost);
    void deleteBlogPost(Long id);
}
