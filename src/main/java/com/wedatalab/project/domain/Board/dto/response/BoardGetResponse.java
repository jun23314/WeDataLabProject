package com.wedatalab.project.domain.Board.dto.response;

import java.time.LocalDateTime;
import lombok.Builder;

public record BoardGetResponse(
    String title,
    String content,
    String name,
    int likes,
    LocalDateTime createdAt
) {
}
