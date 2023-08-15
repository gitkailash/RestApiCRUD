package com.treeleaf.restapi.service.impl;

import com.treeleaf.restapi.entity.BlogPost;
import com.treeleaf.restapi.exception.BlogPostNotFoundException;
import com.treeleaf.restapi.exception.UserNotFoundException;
import com.treeleaf.restapi.repository.BlogPostRepo;
import com.treeleaf.restapi.service.BlogPostService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class BlogPostServiceImpl implements BlogPostService {

    private final BlogPostRepo blogPostRepo;
    @Override
    public List<BlogPost> getAllBlogPosts() {
        return blogPostRepo.findAll();
    }

    @Override
    public BlogPost getBlogPostById(Long id) {
        Optional<BlogPost> checkBlogPost = blogPostRepo.findById(id);
        if (checkBlogPost.isPresent()) {
            return checkBlogPost.get();
        }else {
            log.error("Blog Post Search Error.");
            throw new BlogPostNotFoundException("Blog Post Not Found.");
        }
    }
    @Override
    public BlogPost saveBlogPost(BlogPost blogPost) {
        log.info("Blog Post save successfully");
        return blogPostRepo.save(blogPost);
    }

    @Override
    public BlogPost updateBlogPost(Long id, BlogPost blogPost) {
        Optional<BlogPost> result = blogPostRepo.findById(id);
        if (result.isPresent()) {
            BlogPost blogPostPersistent = result.get();
            blogPostPersistent.setTitle(blogPost.getTitle());
            blogPostPersistent.setContent(blogPost.getContent());
            blogPostRepo.save(blogPostPersistent);

            log.info("user update for id " + id);
            return blogPostPersistent;
        }else {
            log.info("user search error for id " + id);
            throw new UserNotFoundException("BlogPost Not Found.");
        }
    }

    @Override
    public void deleteBlogPost(Long id) {
        Optional<BlogPost> user = blogPostRepo.findById(id);
        if (user.isEmpty()) {
            log.error("BlogPost Search Error for id "+ id);
            throw new BlogPostNotFoundException("BlogPost Not Found.");
        }else {
            log.info("Blog Post delete successful.");
            blogPostRepo.deleteById(id);
        }
    }
}
