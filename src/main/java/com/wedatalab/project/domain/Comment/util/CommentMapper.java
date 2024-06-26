package com.wedatalab.project.domain.Comment.util;

import com.wedatalab.project.domain.Board.entity.Board;
import com.wedatalab.project.domain.Comment.dto.response.CommentGetResponse;
import com.wedatalab.project.domain.Comment.entity.Comment;
import com.wedatalab.project.domain.User.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentMapper {

    public static Comment toComment(User user, Board board, String content) {
        return Comment.builder()
            .board(board)
            .user(user)
            .content(content)
            .build();
    }

    public static CommentGetResponse fromComment(Comment comment) {
        if(comment.getUser().getIsDeleted().equals(true)){
            return CommentGetResponse.builder()
                .name("삭제된 유저입니다")
                .content(comment.getContent())
                .likes(comment.getLikes())
                .createdAt(comment.getCreatedAt())
                .build();
        }
        return CommentGetResponse.builder()
            .name(comment.getUser().getName())
            .content(comment.getContent())
            .likes(comment.getLikes())
            .createdAt(comment.getCreatedAt())
            .build();
    }

}
