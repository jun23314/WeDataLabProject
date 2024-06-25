package com.wedatalab.project.domain.Comment.controller;

import com.wedatalab.project.domain.Comment.dto.request.CommentCreateRequest;
import com.wedatalab.project.domain.Comment.dto.response.CommentGetResponse;
import com.wedatalab.project.domain.Comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("v1/api/comment")
@Tag(name = "comment api", description = "comment 관련 API")
public class CommentRestController {

    private final CommentService commentService;

    @PostMapping("/create")
    @Operation(summary = "comment 저장 API", description = "board에 대한 user의 comment를 DB에 저장하는 API입니다.")
    public ResponseEntity<String> createComment(
        @RequestBody CommentCreateRequest commentCreateRequest
    ) {
        commentService.createComment(commentCreateRequest);
        return ResponseEntity.ok("코멘트 생성을 완료하였습니다.");
    }

    @PutMapping("/update")
    @Operation(summary = "comment update API", description = "comment를 update하는 API입니다.")
    public ResponseEntity<String> updateComment(
        @RequestParam Long commentId,
        @RequestParam String content
    ) {
        commentService.updateComment(commentId, content);
        return ResponseEntity.ok("코멘트를 성공적으로 수정하였습니다.");
    }

    @GetMapping("/get/{board_id}")
    @Operation(summary = "comment get API", description = "board에 있는 comment를 get하는 API입니다")
    public ResponseEntity<List<CommentGetResponse>> getComment(
        @PathVariable("board_id") Long boardId
    ) {
        List<CommentGetResponse> commentList = commentService.getComment(boardId);
        return ResponseEntity.ok(commentList);


    }

}
