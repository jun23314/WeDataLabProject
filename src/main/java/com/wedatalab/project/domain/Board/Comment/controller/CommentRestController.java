package com.wedatalab.project.domain.Board.Comment.controller;

import com.wedatalab.project.domain.Board.Comment.dto.request.CommentCreateRequest;
import com.wedatalab.project.domain.Board.Comment.dto.response.CommentGetResponse;
import com.wedatalab.project.domain.Board.Comment.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
@RequiredArgsConstructor
@RequestMapping("boards/{board_id}/comments")
@Tag(name = "comment api", description = "comment 관련 API")
public class CommentRestController {

    private final CommentService commentService;

    @PostMapping("")
    @Operation(summary = "comment 저장 API", description = "board에 대한 user의 comment를 DB에 저장하는 API입니다.")
    public ResponseEntity<String> createComment(
        @PathVariable("board_id") Long boardId,
        @RequestBody CommentCreateRequest commentCreateRequest
    ) {
        commentService.createComment(commentCreateRequest, boardId);
        return ResponseEntity.ok("코멘트 생성을 완료하였습니다.");
    }

    @PostMapping("/{comment_id}/like")
    @Operation(summary = "커멘트 좋아요 API", description = "사용자가 커멘트에 좋아요를 누를 때 사용되는 API입니다.")
    public ResponseEntity<String> postCommentLikes(
        @PathVariable("board_id") Long boardId,
        @PathVariable("comment_id") Long commentId,
        @RequestParam Long userId
    ) {
        String result = commentService.createCommentLikes(userId, commentId);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/{comment_id}")
    @Operation(summary = "comment update API", description = "comment를 update하는 API입니다.")
    public ResponseEntity<String> updateComment(
        @PathVariable("board_id") Long boardId,
        @PathVariable("comment_id") Long commentId,
        @Size(max = 100) @NotEmpty @NotNull @RequestParam String content
    ) {
        commentService.updateComment(commentId, content);
        return ResponseEntity.ok("코멘트를 성공적으로 수정하였습니다.");
    }

    @GetMapping("")
    @Operation(summary = "comment get API", description = "board에 있는 comment를 get하는 API입니다")
    public ResponseEntity<List<CommentGetResponse>> getComment(
        @PathVariable("board_id") Long boardId
    ) {
        List<CommentGetResponse> commentList = commentService.getComment(boardId);
        return ResponseEntity.ok(commentList);
    }

    @DeleteMapping("/{comment_id}")
    @Operation(summary = "comment delete api", description = "comment를 삭제하는 API입니다.")
    public ResponseEntity<String> deleteComment(
        @PathVariable("board_id") Long boardId,
        @PathVariable("comment_id") Long commentId
    ) {
        commentService.deleteComment(commentId);
        return ResponseEntity.ok("comment를 성공적으로 삭제하였습니다.");
    }

}
