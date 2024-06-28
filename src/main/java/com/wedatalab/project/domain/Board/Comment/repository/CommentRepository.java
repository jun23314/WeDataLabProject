package com.wedatalab.project.domain.Board.Comment.repository;

import com.wedatalab.project.domain.Board.Comment.entity.Comments;
import com.wedatalab.project.domain.Board.entity.Board;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comments, Long> {
    List<Comments> findAllByBoard(Board board);

}
