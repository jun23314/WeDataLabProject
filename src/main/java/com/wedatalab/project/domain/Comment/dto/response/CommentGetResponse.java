package com.wedatalab.project.domain.Comment.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;

public record CommentGetResponse(
    String name,
    String content,

    int likes,
    LocalDateTime createdAt
) {

    @Builder
    public CommentGetResponse {
    }
}
