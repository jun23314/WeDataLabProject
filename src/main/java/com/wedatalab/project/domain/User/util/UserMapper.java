package com.wedatalab.project.domain.User.util;

import com.wedatalab.project.domain.User.dto.request.UserCreateRequest;
import com.wedatalab.project.domain.User.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static User toUser(UserCreateRequest userCreateRequest) {
        return User.builder()
            .nickname(userCreateRequest.nickname())
            .name(userCreateRequest.name())
            .age(userCreateRequest.age())
            .build();
    }

}
