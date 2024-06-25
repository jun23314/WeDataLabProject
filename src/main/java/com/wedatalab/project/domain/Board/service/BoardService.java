package com.wedatalab.project.domain.Board.service;

import com.wedatalab.project.domain.Board.dto.request.BoardUpdateRequest;
import com.wedatalab.project.domain.Board.dto.request.CreateBoardRequest;
import com.wedatalab.project.domain.Board.entity.Board;
import com.wedatalab.project.domain.Board.repository.BoardRepository;
import com.wedatalab.project.domain.Board.util.BoardMapper;
import com.wedatalab.project.domain.Comment.exception.BoardNotFoundException;
import com.wedatalab.project.domain.User.exception.UserNotFoundException;
import com.wedatalab.project.domain.User.repository.UserRepository;
import com.wedatalab.project.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public void createBoard(CreateBoardRequest createBoardRequest) {
        if (!userRepository.existsByIdAndIsDeletedIsFalse(createBoardRequest.userId())) {
            throw new UserNotFoundException(ErrorCode.USER_NOT_FOUND);
        }

        Board board = BoardMapper.toBoard(createBoardRequest);
        boardRepository.save(board);
    }

    @Transactional
    public void updateBoard(BoardUpdateRequest boardUpdateRequest, Long boardId) {
        if (!userRepository.existsByIdAndIsDeletedIsFalse(boardUpdateRequest.userId())) {
            throw new UserNotFoundException(ErrorCode.USER_NOT_FOUND);
        }

        Board board = boardRepository.findByIdAndIsDeletedIsFalse(boardId).orElseThrow(
            () -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND)
        );

        board.updateBoard(boardUpdateRequest.title(), boardUpdateRequest.content());
        boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Long boardId) {
        Board board = boardRepository.findByIdAndIsDeletedIsFalse(boardId).orElseThrow(
            () -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND)
        );
        board.deleteBoard();
    }

}
