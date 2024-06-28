package com.wedatalab.project.domain.Board.controller;

import com.wedatalab.project.domain.Board.dto.request.BoardUpdateRequest;
import com.wedatalab.project.domain.Board.dto.request.CreateBoardRequest;
import com.wedatalab.project.domain.Board.dto.response.BoardGetResponse;
import com.wedatalab.project.domain.Board.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("boards")
@RequiredArgsConstructor
@Tag(name = "board api", description = "board 관련 API")
public class BoardRestController {

    private final BoardService boardService;

    @PostMapping("/{user_id}")
    @Operation(summary = "board 저장 API", description = "board를 DB에 저장하는 API입니다.")
    public ResponseEntity<String> createBoard(
        @PathVariable("user_id") Long userId,
        @RequestBody CreateBoardRequest createBoardRequest
    ) {
        boardService.createBoard(createBoardRequest, userId);
        return new ResponseEntity<>("보드 생성을 완료하였습니다.", HttpStatus.CREATED);

    }

    @PutMapping("/{board_id}")
    @Operation(summary = "board update API", description = "board를 update하는 API입니다.")
    public ResponseEntity<String> updateBoard(
        @PathVariable("board_id") Long boardId,
        @RequestBody BoardUpdateRequest boardUpdateRequest
    ) {
        boardService.updateBoard(boardUpdateRequest, boardId);
        return new ResponseEntity<>("보드 업데이트를 완료하였습니다.", HttpStatus.OK);
    }

    @DeleteMapping("/{board_id}")
    @Operation(summary = "board 삭제 API", description = "board를 삭제하는 API입니다.")
    public ResponseEntity<String> deleteBoard(
        @PathVariable("board_id") Long boardId
    ) {
        boardService.deleteBoard(boardId);
        return new ResponseEntity<>("보드 삭제를 완료하였습니다.", HttpStatus.OK);
    }

    @PostMapping("/{board_id}/like")
    @Operation(summary = "보드 좋아요 API", description = "사용자가 보드에 좋아요를 누를 때 사용되는 API입니다.")
    public ResponseEntity<String> getBoardLikes(
        @RequestParam Long userId,
        @PathVariable("board_id") Long boardId
    ) {
        return new ResponseEntity<>(boardService.createBoardLikes(userId, boardId), HttpStatus.OK);
    }

    @GetMapping("")
    @Operation(summary = "보드 전체 조회 API", description = "보드 전체 정보를 조회하는 API입니다.")
    public ResponseEntity<List<BoardGetResponse>> getBoardAll(){
        List<BoardGetResponse> boardGetResponseList = boardService.getBoardAll();
        return new ResponseEntity<>(boardGetResponseList, HttpStatus.OK);
    }

    @GetMapping("/{board_id}")
    @Operation(summary = "보드 상세 조회 API", description = "보드 상세 정보를 조회하는 API입니다.")
    public ResponseEntity<BoardGetResponse> getBoardDetail(
        @PathVariable("board_id") Long boardId
    ) {
        BoardGetResponse boardGetResponse = boardService.getBoardDetail(boardId);
        return new ResponseEntity<>(boardGetResponse, HttpStatus.OK);
    }




}
