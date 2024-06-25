package com.wedatalab.project.domain.User.dto.request;

import lombok.Builder;

public record UserCreateRequest(
    String nickname,
    String name,
    Integer age
) {

    @Builder
    public UserCreateRequest {
    }
}
