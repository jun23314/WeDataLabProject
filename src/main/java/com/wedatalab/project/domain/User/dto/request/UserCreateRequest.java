package com.wedatalab.project.domain.User.dto.request;

public record UserCreateRequest(
    String email,
    String name,
    Integer age
) {

}
