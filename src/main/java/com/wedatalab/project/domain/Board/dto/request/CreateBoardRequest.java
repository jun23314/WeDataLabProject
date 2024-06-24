package com.wedatalab.project.domain.Board.dto.request;

import lombok.Builder;

public record CreateBoardRequest(
    String title,
    String content
) {

    @Builder
    public CreateBoardRequest {
    }
}
