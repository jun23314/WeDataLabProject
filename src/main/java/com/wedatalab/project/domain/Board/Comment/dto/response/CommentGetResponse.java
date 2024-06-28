package com.wedatalab.project.domain.Board.Comment.dto.response;

import java.time.LocalDateTime;

public record CommentGetResponse(
    String name,
    String content,
    LocalDateTime createdAt,
    int likes
) {

}
