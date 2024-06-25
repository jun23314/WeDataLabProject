package com.wedatalab.project.domain.User.util;

import com.wedatalab.project.domain.User.dto.request.UserCreateRequest;
import com.wedatalab.project.domain.User.dto.response.UserGetResponse;
import com.wedatalab.project.domain.User.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static User toUser(UserCreateRequest userCreateRequest) {
        return User.builder()
            .email(userCreateRequest.email())
            .name(userCreateRequest.name())
            .age(userCreateRequest.age())
            .build();
    }

    public static UserGetResponse fromUser(User user) {
        return UserGetResponse.builder()
            .name(user.getName())
            .age(user.getAge())
            .email(user.getEmail())
            .build();
    }

}
