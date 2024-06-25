package com.wedatalab.project.domain.User.entity;

import com.wedatalab.project.domain.Board.entity.Board;
import com.wedatalab.project.global.common.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
public class User extends BaseEntity {

    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("유저 이름")
    @Column(nullable = false, length = 10)
    private String name;

    @Comment("유저 나이")
    private Integer age;

    @Comment("유저 nickname")
    @Column(nullable = false, length = 20)
    private String nickname;

    @Comment("유저 삭제 여부")
    private Boolean isDeleted = false;

    @Comment("comment relation")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    List<com.wedatalab.project.domain.Comment.entity.Comment> commentList = new ArrayList<>();

    @Comment("board relation for 작성자")
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Board> boardList = new ArrayList<>();

    @Builder
    public User(Long id, String name, Integer age, String nickname,
        List<com.wedatalab.project.domain.Comment.entity.Comment> commentList,
        List<Board> boardList) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.nickname = nickname;
        this.commentList = commentList;
        this.boardList = boardList;
    }

    public void updateUser(String name, Integer age, String nickname) {
        this.name = name;
        this.age = age;
        this.nickname = nickname;
    }

    public void deleteUser(){
        this.isDeleted = true;
        this.delete(LocalDateTime.now());
    }
}
