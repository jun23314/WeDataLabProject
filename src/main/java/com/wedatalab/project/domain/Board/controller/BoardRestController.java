package com.wedatalab.project.domain.Board.controller;

import com.wedatalab.project.domain.Board.dto.request.BoardUpdateRequest;
import com.wedatalab.project.domain.Board.dto.request.CreateBoardRequest;
import com.wedatalab.project.domain.Board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/board")
@RequiredArgsConstructor
@Tag(name = "board api", description = "board 관련 API")
public class BoardRestController {

    private final BoardService boardService;


    @PostMapping("/create")
    @Operation(summary = "board 저장 API", description = "board를 DB에 저장하는 API입니다.")
    public ResponseEntity<String> createBoard(
        @RequestBody CreateBoardRequest createBoardRequest
    ) {
        boardService.createBoard(createBoardRequest);
        return ResponseEntity.ok("게시판을 성공적으로 생성하였습니다.");
    }

    @PutMapping("/update/{board_id}")
    @Operation(summary = "board update API", description = "board를 update하는 API입니다.")
    public ResponseEntity<String> updateBoard(
        @PathVariable("board_id") Long boardId,
        @RequestBody BoardUpdateRequest boardUpdateRequest
    ) {
        boardService.updateBoard(boardUpdateRequest, boardId);
        return ResponseEntity.ok("게시판을 성공적으로 업데이트하였습니다.");
    }


}
