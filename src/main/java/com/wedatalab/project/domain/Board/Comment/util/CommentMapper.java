package com.wedatalab.project.domain.Board.Comment.util;

import com.wedatalab.project.domain.Board.Comment.entity.Comments;
import com.wedatalab.project.domain.Board.entity.Board;
import com.wedatalab.project.domain.Board.Comment.dto.request.CommentCreateRequest;
import com.wedatalab.project.domain.Board.Comment.dto.response.CommentGetResponse;
import com.wedatalab.project.domain.User.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class CommentMapper {

    public static Comments toComment(User user, Board board,
        CommentCreateRequest commentCreateRequest) {
        return new Comments(commentCreateRequest.content(), board, user);
    }

    public static CommentGetResponse fromComment(Comments comments, int likes) {
        return new CommentGetResponse(comments.getUser().getName(), comments.getContent(),
            comments.getCreatedAt(), likes);
    }


}
