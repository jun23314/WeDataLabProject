package com.wedatalab.project.domain.User.dto.request;

import lombok.Builder;

public record UserUpdateRequest(
    Long id,
    String name,
    Integer age,
    String nickname
) {

    @Builder
    public UserUpdateRequest {
    }
}
