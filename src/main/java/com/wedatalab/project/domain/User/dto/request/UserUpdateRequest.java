package com.wedatalab.project.domain.User.dto.request;

import lombok.Builder;

public record UserUpdateRequest(
    String name,
    Integer age,
    String email
) {

    @Builder
    public UserUpdateRequest {
    }
}
