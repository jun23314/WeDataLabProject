package com.wedatalab.project.domain.Board.entity;

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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Board extends BaseEntity {

    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("Board 제목")
    @Column(length = 100)
    private String title;

    @Comment("Board 내용")
    @Column(length = 100, nullable = false)
    private String content;

    @Comment("게시글 삭제 여부")
    private Boolean isDeleted = false;

    @ManyToMany
    @JoinTable(name = "board_user")
    private List<User> users = new ArrayList<>();

    @Comment("comment relation")
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL)
    private List<com.wedatalab.project.domain.Comment.entity.Comment> commentList = new ArrayList<>();

    @Comment("user relation for 작성자")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @Builder
    public Board(Long id, String title, String content, List<User> users,
        List<com.wedatalab.project.domain.Comment.entity.Comment> commentList, User user) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.users = users;
        this.commentList = commentList;
        this.user = user;
    }

    public void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public void deleteBoard() {
        this.isDeleted = true;
        this.delete(LocalDateTime.now());
    }

    public void updateUsers(User user) {
        this.users.add(user);
    }
}
