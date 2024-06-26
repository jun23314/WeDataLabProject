package com.wedatalab.project.domain.User.util;

import com.wedatalab.project.domain.Comment.entity.Comment;
import com.wedatalab.project.domain.User.entity.User;
import com.wedatalab.project.domain.User.entity.UserLikesComment;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class UserLikesCommentMapper {

    public static UserLikesComment toUserLikesComment(User user, Comment comment) {
        return UserLikesComment.builder()
            .user(user)
            .comment(comment)
            .build();
    }

}
