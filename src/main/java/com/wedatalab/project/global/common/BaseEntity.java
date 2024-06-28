package com.wedatalab.project.global.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    @Setter
    @CreatedDate
    @Comment("생성일")
    private LocalDateTime createdAt;

    @Column(insertable = false)
    @LastModifiedDate
    @Comment("업데이트일")
    private LocalDateTime updatedAt;
}
