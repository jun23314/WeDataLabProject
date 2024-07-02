package com.wedatalab.project.domain.Board.service;

import com.wedatalab.project.domain.Board.dto.request.BoardUpdateRequest;
import com.wedatalab.project.domain.Board.dto.request.CreateBoardRequest;
import com.wedatalab.project.domain.Board.dto.response.BoardGetResponse;
import com.wedatalab.project.domain.Board.entity.Board;
import com.wedatalab.project.domain.Board.exception.AlreadyLikedBoardException;
import com.wedatalab.project.domain.Board.repository.BoardRepository;
import com.wedatalab.project.domain.Board.util.BoardMapper;
import com.wedatalab.project.domain.Board.Comment.exception.BoardNotFoundException;
import com.wedatalab.project.domain.User.entity.User;
import com.wedatalab.project.domain.User.exception.UserNotFoundException;
import com.wedatalab.project.domain.User.repository.UserRepository;
import com.wedatalab.project.global.exception.ErrorCode;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;
    private final UserRepository userRepository;

    @Transactional
    public Board createBoard(CreateBoardRequest createBoardRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
            () -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND)
        );

        Board board = BoardMapper.toBoard(createBoardRequest, user);
        boardRepository.save(board);
        return board;
    }

    @Transactional
    public void updateBoard(BoardUpdateRequest boardUpdateRequest, Long boardId) {
        if (!userRepository.existsById(boardUpdateRequest.userId())) {
            throw new UserNotFoundException(ErrorCode.USER_NOT_FOUND);
        }

        Board board = boardRepository.findById(boardId).orElseThrow(
            () -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND)
        );

        board.updateBoard(boardUpdateRequest.title(), boardUpdateRequest.content());
        boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
            () -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND)
        );

        boardRepository.delete(board);
    }

    @Transactional
    public String createBoardLikes(Long userId, Long boardId) {
        User user = userRepository.findById(userId).orElseThrow(
            () -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND)
        );

        Board board = boardRepository.findById(boardId).orElseThrow(
            () -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND)
        );

        Boolean result = board.updateUsers(user);
        boardRepository.save(board);

        return result ? "보드 좋아요를 등록하였습니다." : "보드 좋아요를 취소하였습니다.";
    }

    @Transactional
    public BoardGetResponse getBoardDetail(Long boardId) {
        Board board = boardRepository.findById(boardId).orElseThrow(
            () -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND)
        );

        return BoardMapper.fromBoard(board);
    }

    @Transactional
    public List<BoardGetResponse> getBoardAll() {
        List<Board> boardList = boardRepository.findAll();
        if(boardList.isEmpty()) throw new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND);

        List<BoardGetResponse> boardGetResponseList = new ArrayList<>();
        for(Board board : boardList){
            BoardGetResponse boardGetResponse = this.getBoardDetail(board.getId());
            boardGetResponseList.add(boardGetResponse);
        }

        return boardGetResponseList;
    }

}
