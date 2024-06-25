package com.wedatalab.project.domain.Board.service;

import com.wedatalab.project.domain.Board.dto.request.BoardUpdateRequest;
import com.wedatalab.project.domain.Board.dto.request.CreateBoardRequest;
import com.wedatalab.project.domain.Board.entity.Board;
import com.wedatalab.project.domain.Board.repository.BoardRepository;
import com.wedatalab.project.domain.Board.util.BoardMapper;
import com.wedatalab.project.domain.Comment.exception.BoardNotFoundException;
import com.wedatalab.project.global.exception.ErrorCode;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class BoardService {

    private final BoardRepository boardRepository;

    @Transactional
    public void createBoard(CreateBoardRequest createBoardRequest) {
        Board board = BoardMapper.toBoard(createBoardRequest);
        log.info(board.getTitle());
        boardRepository.save(board);
    }

    @Transactional
    public void updateBoard(BoardUpdateRequest boardUpdateRequest) {
        log.info(boardUpdateRequest.boardId().toString());
        Optional<Board> optionalBoard = boardRepository.findById(boardUpdateRequest.boardId());
        log.info("그니까 이건 되는거야?");

        Board board = boardRepository.findById(boardUpdateRequest.boardId()).orElseThrow(
            () -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND)
        );

        board.updateBoard(boardUpdateRequest.title(), boardUpdateRequest.content());
        boardRepository.save(board);
    }

}
