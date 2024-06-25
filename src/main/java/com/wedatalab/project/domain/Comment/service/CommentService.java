package com.wedatalab.project.domain.Comment.service;

import com.wedatalab.project.domain.Board.entity.Board;
import com.wedatalab.project.domain.Board.repository.BoardRepository;
import com.wedatalab.project.domain.Comment.dto.request.CommentCreateRequest;
import com.wedatalab.project.domain.Comment.dto.response.CommentGetResponse;
import com.wedatalab.project.domain.Comment.entity.Comment;
import com.wedatalab.project.domain.Comment.exception.BoardNotFoundException;
import com.wedatalab.project.domain.Comment.exception.CommentNotExistException;
import com.wedatalab.project.domain.Comment.exception.CommentNotFoundException;
import com.wedatalab.project.domain.Comment.exception.DeletedBoardException;
import com.wedatalab.project.domain.Comment.repository.CommentRepository;
import com.wedatalab.project.domain.Comment.util.CommentMapper;
import com.wedatalab.project.domain.User.entity.User;
import com.wedatalab.project.domain.User.exception.UserNotFoundException;
import com.wedatalab.project.domain.User.repository.UserRepository;
import com.wedatalab.project.global.exception.ErrorCode;
import java.util.ArrayList;
import java.util.List;
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

        User user = userRepository.findByIdAndIsDeletedIsFalse(userId).orElseThrow(
            () -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND)
        );

        Board board = boardRepository.findByIdAndIsDeletedIsFalse(boardId).orElseThrow(
            () -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND)
        );

        Comment comment = CommentMapper.toComment(user, board, content);
        commentRepository.save(comment);
    }

    @Transactional
    public void updateComment(Long commentId, String content) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
            () -> new CommentNotFoundException(ErrorCode.COMMENT_NOT_FOUND)
        );
        if (comment.getBoard().getIsDeleted().equals(true)) {
            throw new DeletedBoardException(ErrorCode.DELETED_BOARD);
        }

        comment.updateComment(content);
        commentRepository.save(comment);

    }

    @Transactional
    public void deleteComment(Long commentId) {
        commentRepository.deleteById(commentId);
    }

    @Transactional
    public List<CommentGetResponse> getComment(Long boardId) {
        Board board = boardRepository.findByIdAndIsDeletedIsFalse(boardId).orElseThrow(
            () -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND)
        );

        List<Comment> commentList = commentRepository.findAllByBoard(board);
        if (commentList.isEmpty()) {
            throw new CommentNotExistException(ErrorCode.COMMENT_NOT_EXIST);
        }

        List<CommentGetResponse> returnCommentList = new ArrayList<>();
        for (Comment comment : commentList) {
            CommentGetResponse commentGetResponse = CommentMapper.fromComment(comment);
            returnCommentList.add(commentGetResponse);
        }

        return returnCommentList;
    }

}
