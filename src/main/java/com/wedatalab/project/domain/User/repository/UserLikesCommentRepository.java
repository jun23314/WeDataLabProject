package com.wedatalab.project.domain.User.repository;

import com.wedatalab.project.domain.Comment.entity.Comment;
import com.wedatalab.project.domain.User.entity.User;
import com.wedatalab.project.domain.User.entity.UserLikesComment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserLikesCommentRepository extends JpaRepository<UserLikesComment, Long> {

    Boolean existsByUserAndComment(User user, Comment comment);

}
