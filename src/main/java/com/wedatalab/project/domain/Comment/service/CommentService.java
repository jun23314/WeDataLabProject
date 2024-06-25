package com.wedatalab.project.domain.Comment.service;

import com.wedatalab.project.domain.Board.entity.Board;
import com.wedatalab.project.domain.Board.repository.BoardRepository;
import com.wedatalab.project.domain.Comment.dto.request.CommentCreateRequest;
import com.wedatalab.project.domain.Comment.entity.Comment;
import com.wedatalab.project.domain.Comment.exception.BoardNotFoundException;
import com.wedatalab.project.domain.Comment.repository.CommentRepository;
import com.wedatalab.project.domain.Comment.util.CommentMapper;
import com.wedatalab.project.domain.User.entity.User;
import com.wedatalab.project.domain.User.exception.UserNotFoundException;
import com.wedatalab.project.domain.User.repository.UserRepository;
import com.wedatalab.project.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void createComment(CommentCreateRequest commentCreateRequest) {
        Long userId = commentCreateRequest.userId();
        Long boardId = commentCreateRequest.boardId();
        String content = commentCreateRequest.content();

        User user = userRepository.findById(userId).orElseThrow(
            () -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND)
        );

        Board board = boardRepository.findById(boardId).orElseThrow(
            () -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND)
        );

        Comment comment = CommentMapper.toComment(user, board, content);
        commentRepository.save(comment);
    }

}
