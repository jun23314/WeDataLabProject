package com.wedatalab.project.domain.User.entity;


import com.wedatalab.project.domain.Board.Comment.entity.Comments;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.io.Serializable;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@Embeddable
@NoArgsConstructor
@EqualsAndHashCode
public class UserLikesComment implements Serializable{
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "comment_id")
    private Comments comments;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public UserLikesComment(User user, Comments comments){
        this.user = user;
        this.comments = comments;
    }
}
