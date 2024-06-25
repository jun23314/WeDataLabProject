package com.wedatalab.project.domain.Board.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public record BoardUpdateRequest(
    Long userId,
    @NotNull
    String title,
    @NotNull
    String content
) {

    @Builder
    public BoardUpdateRequest {
    }
}
