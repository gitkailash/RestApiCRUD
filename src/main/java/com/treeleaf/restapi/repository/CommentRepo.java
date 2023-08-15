package com.treeleaf.restapi.repository;

import com.treeleaf.restapi.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}
