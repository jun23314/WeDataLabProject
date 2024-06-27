package com.wedatalab.project.domain.Comment.entity;


import com.wedatalab.project.domain.Board.entity.Board;
import com.wedatalab.project.domain.User.entity.User;
import com.wedatalab.project.domain.User.entity.UserLikesComment;
import com.wedatalab.project.global.common.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @org.hibernate.annotations.Comment("comment 내용")
    @Column(length = 100)
    private String content;

    @org.hibernate.annotations.Comment("comment 삭제 여부")
    private Boolean isDeleted;

    @org.hibernate.annotations.Comment("좋아요 수")
    private int likes;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @org.hibernate.annotations.Comment("user relation for 좋아요")
    @OneToMany(mappedBy = "comment", cascade = CascadeType.MERGE)
    private List<UserLikesComment> userLikesComments = new ArrayList<>();

    @Builder
    public Comment(Long id, String content, Board board, User user,
        List<UserLikesComment> userLikesComments) {
        this.id = id;
        this.content = content;
        this.isDeleted = false;
        this.likes = 0;
        this.board = board;
        this.user = user;
        this.userLikesComments = userLikesComments;
    }

    public void updateComment(String content) {
        this.content = content;
    }

    public void updateCommentLikes(){
        this.likes = this.likes + 1;
    }

    public void deleteComment() {
        this.isDeleted = true;
        this.delete(LocalDateTime.now());
    }
}
