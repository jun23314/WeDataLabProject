package com.wedatalab.project.domain.User.util;

import com.wedatalab.project.domain.User.dto.request.UserCreateRequest;
import com.wedatalab.project.domain.User.dto.response.UserGetResponse;
import com.wedatalab.project.domain.User.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserMapper {

    public static User toUser(UserCreateRequest userCreateRequest) {
        return new User(userCreateRequest.name(), userCreateRequest.age(),
            userCreateRequest.email());
    }

    public static UserGetResponse fromUser(User user) {
        return new UserGetResponse(user.getName(), user.getAge(), user.getEmail());
    }

}
