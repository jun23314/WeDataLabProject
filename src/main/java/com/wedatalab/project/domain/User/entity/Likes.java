package com.wedatalab.project.domain.User.entity;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Likes {

    @EmbeddedId
    private UserLikesComment id;

    public Likes(UserLikesComment userLikesComment) {
        this.id = userLikesComment;
    }

}