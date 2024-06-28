package com.wedatalab.project.domain.Board.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public record CreateBoardRequest(
    @NotNull
    String title,
    @NotNull
    String content
) {
}
