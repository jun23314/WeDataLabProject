package com.wedatalab.project.domain.Comment.repository;

import com.wedatalab.project.domain.Comment.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}
