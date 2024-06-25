package com.wedatalab.project.domain.Comment.dto.request;

import lombok.Builder;

public record CommentCreateRequest(
    Long userId,
    Long boardId,
    String content
) {

    @Builder
    public CommentCreateRequest {
    }
}
