package com.wedatalab.project.domain.Board.controller;

import com.wedatalab.project.domain.Board.dto.request.BoardUpdateRequest;
import com.wedatalab.project.domain.Board.dto.request.CreateBoardRequest;
import com.wedatalab.project.domain.Board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/board")
@RequiredArgsConstructor
@Tag(name = "board api", description = "board 관련 API")
public class BoardRestController {

    private final BoardService boardService;

    @PostMapping("/create/{user_id}")
    @Operation(summary = "board 저장 API", description = "board를 DB에 저장하는 API입니다.")
    public ResponseEntity<String> createBoard(
        @PathVariable("user_id") Long userId,
        @RequestBody CreateBoardRequest createBoardRequest
    ) {
        boardService.createBoard(createBoardRequest, userId);
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

    @DeleteMapping("/delete/{board_id}")
    @Operation(summary = "board 삭제 API", description = "board를 삭제하는 API입니다.")
    public ResponseEntity<String> deleteBoard(
        @PathVariable("board_id") Long boardId
    ) {
        boardService.deleteBoard(boardId);
        return ResponseEntity.ok("게시판을 성공적으로 삭제하였습니다.");
    }

    @PostMapping("/post/likes/{board_id}")
    @Operation(summary = "보드 좋아요 API", description = "사용자가 보드에 좋아요를 누를 때 사용되는 API입니다.")
    public ResponseEntity<String> getBoardLikes(
        @RequestParam Long userId,
        @PathVariable("board_id") Long boardId
    ) {
        boardService.getBoardLikes(userId, boardId);
        return ResponseEntity.ok("좋아요를 등록했습니다.");
    }


}
