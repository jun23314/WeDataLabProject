package com.wedatalab.project.domain.User.dto.response;

import lombok.Builder;

public record UserGetResponse(
    String name,
    Integer age,
    String email
) {

    @Builder
    public UserGetResponse {
    }
}
