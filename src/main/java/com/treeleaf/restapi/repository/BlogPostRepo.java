package com.treeleaf.restapi.repository;

import com.treeleaf.restapi.entity.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BlogPostRepo extends JpaRepository<BlogPost, Long> {
}
