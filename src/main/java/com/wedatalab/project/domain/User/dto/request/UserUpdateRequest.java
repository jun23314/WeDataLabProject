package com.wedatalab.project.domain.User.dto.request;

public record UserUpdateRequest(
    String name,
    Integer age,
    String email
) {

}
