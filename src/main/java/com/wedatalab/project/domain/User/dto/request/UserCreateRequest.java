package com.wedatalab.project.domain.User.dto.request;

import lombok.Builder;

public record UserCreateRequest(
    String user_id,
    String name,
    Integer age
) {

    @Builder
    public UserCreateRequest {
    }
}
