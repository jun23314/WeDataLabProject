package com.wedatalab.project.domain.Board.Comment.entity;


import com.wedatalab.project.domain.Board.entity.Board;
import com.wedatalab.project.domain.User.entity.Likes;
import com.wedatalab.project.domain.User.entity.User;
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
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comments extends BaseEntity {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @org.hibernate.annotations.Comment("comment 내용")
    @Column(length = 100)
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @org.hibernate.annotations.Comment("user relation for 좋아요")
    @OneToMany(mappedBy = "id.comments", cascade = CascadeType.ALL)
    private List<Likes> likes;

    public Comments(String content, Board board, User user) {
        this.content = content;
        this.board = board;
        this.user = user;
        this.likes = new ArrayList<>();
    }

    public void updateComment(String content) {
        this.content = content;
    }

    public void updateLikes(List<Likes> likes){
        this.likes = likes;
    }

}
