package com.treeleaf.restapi.service.impl;

import com.treeleaf.restapi.entity.Comment;
import com.treeleaf.restapi.entity.Comment;
import com.treeleaf.restapi.entity.Comment;
import com.treeleaf.restapi.entity.Comment;
import com.treeleaf.restapi.exception.CommentNotFoundException;
import com.treeleaf.restapi.exception.ThumbNailImageNotFoundException;
import com.treeleaf.restapi.repository.CommentRepo;
import com.treeleaf.restapi.service.CommentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
@Slf4j
public class CommentServiceImpl implements CommentService {

    private final CommentRepo commentRepo;
    @Override
    public List<Comment> getAllComments() {
        return commentRepo.findAll();
    }

    @Override
    public Comment getCommentById(Long id) {
        Optional<Comment> checkComment = commentRepo.findById(id);
        if (checkComment.isPresent()) {
            return checkComment.get();
        }else {
            log.error("Comment Search Error.");
            throw new CommentNotFoundException("Comment image Not Found.");
        }
    }

    @Override
    public Comment createComment(Comment comment) {
        log.info("Comment save successfully");
        return commentRepo.save(comment);
    }

    @Override
    public Comment updateComment(Long id, Comment comment) {
        Optional<Comment> result = commentRepo.findById(id);
        if (result.isPresent()) {
            Comment commentPersistent = result.get();
            commentPersistent.setContent(comment.getContent());
            commentPersistent.setCreatedDate(comment.getCreatedDate());
            commentRepo.save(commentPersistent);

            log.info("Thumbnail update for id " + id);
            return commentPersistent;
        }else {
            log.info("Thumbnail search error for id " + id);
            throw new CommentNotFoundException("Comment Not Found.");
        }
    }

    @Override
    public void deleteComment(Long id) {
        Optional<Comment> user = commentRepo.findById(id);
        if (user.isEmpty()) {
            log.error("Comment Search Error for id "+ id);
            throw new CommentNotFoundException("Comment Not Found.");
        }else {
            log.info("Comment delete successful.");
            commentRepo.deleteById(id);
        }
    }
}
