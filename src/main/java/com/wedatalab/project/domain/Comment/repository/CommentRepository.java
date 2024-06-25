package com.wedatalab.project.domain.Comment.repository;

import com.wedatalab.project.domain.Board.entity.Board;
import com.wedatalab.project.domain.Comment.entity.Comment;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

    List<Comment> findAllByBoard(Board board);

}
