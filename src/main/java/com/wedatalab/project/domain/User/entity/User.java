package com.wedatalab.project.domain.User.entity;

import com.wedatalab.project.domain.Board.entity.Board;
import com.wedatalab.project.domain.User.dto.request.UserUpdateRequest;
import com.wedatalab.project.global.common.BaseEntity;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
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

    @NotNull
    @Comment("유저 이름")
    @Column(nullable = false, length = 10)
    private String name;

    @Comment("유저 나이")
    private Integer age;

    @Comment("유저 email")
    @Column(nullable = false, length = 50)
    private String email;

    public User(String name, Integer age, String email) {
        this.name = name;
        this.age = age;
        this.email = email;
    }

    public void updateUser(UserUpdateRequest userUpdateRequest) {
        this.name = userUpdateRequest.name();
        this.age = userUpdateRequest.age();
        this.email = userUpdateRequest.email();
    }
}
