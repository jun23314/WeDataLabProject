package com.wedatalab.project.domain.Comment.dto.request;

import lombok.Builder;

public record CommentCreateRequest(
    Long userId,
    String content
) {

    @Builder
    public CommentCreateRequest {
    }
}
