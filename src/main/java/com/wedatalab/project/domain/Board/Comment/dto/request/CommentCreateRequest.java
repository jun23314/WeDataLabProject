package com.wedatalab.project.domain.Board.Comment.dto.request;

import lombok.Builder;

public record CommentCreateRequest(
    Long userId,
    String content
) {
}
