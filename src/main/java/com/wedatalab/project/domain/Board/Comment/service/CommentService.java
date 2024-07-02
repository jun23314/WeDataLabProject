package com.wedatalab.project.domain.Board.Comment.service;

import com.wedatalab.project.domain.Board.Comment.exception.BoardNotFoundException;
import com.wedatalab.project.domain.Board.entity.Board;
import com.wedatalab.project.domain.Board.repository.BoardRepository;
import com.wedatalab.project.domain.Board.Comment.dto.request.CommentCreateRequest;
import com.wedatalab.project.domain.Board.Comment.dto.response.CommentGetResponse;
import com.wedatalab.project.domain.Board.Comment.entity.Comments;
import com.wedatalab.project.domain.Board.Comment.exception.CommentNotExistException;
import com.wedatalab.project.domain.Board.Comment.exception.CommentNotFoundException;
import com.wedatalab.project.domain.Board.Comment.repository.CommentRepository;
import com.wedatalab.project.domain.Board.Comment.util.CommentMapper;
import com.wedatalab.project.domain.User.entity.Likes;
import com.wedatalab.project.domain.User.entity.User;
import com.wedatalab.project.domain.User.entity.UserLikesComment;
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
    public void createComment(CommentCreateRequest commentCreateRequest, Long boardId) {
        Long userId = commentCreateRequest.userId();

        User user = userRepository.findById(userId).orElseThrow(
            () -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND)
        );

        Board board = boardRepository.findById(boardId).orElseThrow(
            () -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND)
        );

        Comments comment = CommentMapper.toComment(user, board, commentCreateRequest);
        commentRepository.save(comment);
    }

    @Transactional
    public String createCommentLikes(Long userId, Long commentId) {
        User user = userRepository.findById(userId).orElseThrow(
            () -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND)
        );

        Comments comment = commentRepository.findById(commentId).orElseThrow(
            () -> new CommentNotFoundException(ErrorCode.COMMENT_NOT_EXIST)
        );
        Boolean result = comment.updateLikes(new Likes(new UserLikesComment(user, comment)));

        commentRepository.save(comment);

        return result ? "보드 좋아요를 등록하였습니다." : "보드 좋아요를 취소하였습니다.";
    }

    @Transactional
    public void updateComment(Long commentId, String content) {
        Comments comment = commentRepository.findById(commentId).orElseThrow(
            () -> new CommentNotFoundException(ErrorCode.COMMENT_NOT_FOUND)
        );
        comment.updateComment(content);
        commentRepository.save(comment);
    }

    @Transactional
    public List<CommentGetResponse> getComment(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
            () -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND)
        );

        List<Comments> commentList = commentRepository.findAllByBoard(board);
        if (commentList.isEmpty()) {
            throw new CommentNotExistException(ErrorCode.COMMENT_NOT_EXIST);
        }

        List<CommentGetResponse> returnCommentList = new ArrayList<>();
        for (Comments comment : commentList) {
            CommentGetResponse commentGetResponse = CommentMapper.fromComment(comment, comment.countLikes());
            returnCommentList.add(commentGetResponse);
        }

        return returnCommentList;
    }

    @Transactional
    public void deleteComment(Long commentId) {
        Comments comment = commentRepository.findById(commentId).orElseThrow(
            () -> new CommentNotFoundException(ErrorCode.COMMENT_NOT_FOUND)
        );

        commentRepository.delete(comment);
    }







}
