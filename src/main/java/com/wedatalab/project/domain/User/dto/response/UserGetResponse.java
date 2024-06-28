package com.wedatalab.project.domain.User.dto.response;

public record UserGetResponse(
    String name,
    Integer age,
    String email
) {

}
