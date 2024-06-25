package com.wedatalab.project.domain.Comment.controller;

import com.wedatalab.project.domain.Comment.dto.request.CommentCreateRequest;
import com.wedatalab.project.domain.Comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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

}
