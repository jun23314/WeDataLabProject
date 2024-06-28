package com.wedatalab.project.domain.Board.entity;

import com.wedatalab.project.domain.Board.Comment.entity.Comments;
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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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

    @ManyToMany
    @JoinTable(name = "board_user")
    private List<User> users;

    @Comment("comment relation")
    @OneToMany(mappedBy = "board", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Comments> commentList;

    @Comment("user relation for 작성자")
    @OnDelete(action = OnDeleteAction.SET_NULL)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Board(String title, String content, User user) {
        this.title = title;
        this.content = content;
        this.users = new ArrayList<>();
        this.commentList = new ArrayList<>();
        this.user = user;
    }

    public void updateBoard(String title, String content) {
        this.title = title;
        this.content = content;
    }

    public Boolean updateUsers(List<User> users, User user) {
        if(users.contains(user)) {
            this.users.remove(user);
            return false;
        }
        this.users.add(user);
        return true;
    }
}
