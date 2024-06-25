package com.wedatalab.project.domain.Comment.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;

public record CommentGetResponse(
    String userNickname,
    String content,
    LocalDateTime createdAt
) {

    @Builder
    public CommentGetResponse {
    }
}
