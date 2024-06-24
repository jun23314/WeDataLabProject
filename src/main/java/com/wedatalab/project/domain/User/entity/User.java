package com.wedatalab.project.domain.User.entity;

import com.wedatalab.project.global.common.BaseEntity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Comment("유저 이름")
    @Column(nullable = false, length = 10)
    private String name;

    @Comment("유저 나이")
    private Integer age;

    @Comment("유저 id")
    @Column(nullable = false, length = 20)
    private String userId;

    @Builder
    public User(Long id, String name, Integer age, String userId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.userId = userId;
    }
}
