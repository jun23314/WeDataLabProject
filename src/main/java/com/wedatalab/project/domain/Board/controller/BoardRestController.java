package com.wedatalab.project.domain.Board.controller;

import com.wedatalab.project.domain.Board.dto.request.CreateBoardRequest;
import com.wedatalab.project.domain.Board.service.BoardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("v1/api/board")
@RequiredArgsConstructor
public class BoardRestController {

    private final BoardService boardService;


    @PostMapping("/create")
    public ResponseEntity<String> createBoard(
        @RequestBody CreateBoardRequest createBoardRequest
    ) {
        log.info("start");
        boardService.createBoard(createBoardRequest);
        return ResponseEntity.ok("게시판을 성공적으로 생성하였습니다.");
    }


}
