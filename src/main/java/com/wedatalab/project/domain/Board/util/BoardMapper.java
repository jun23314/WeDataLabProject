package com.wedatalab.project.domain.Board.util;

import com.wedatalab.project.domain.Board.dto.request.CreateBoardRequest;
import com.wedatalab.project.domain.Board.dto.response.BoardGetResponse;
import com.wedatalab.project.domain.Board.entity.Board;
import com.wedatalab.project.domain.User.entity.User;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardMapper {

    public static Board toBoard(CreateBoardRequest createBoardRequest, User user) {
        return Board.builder()
            .user(user)
            .content(createBoardRequest.content())
            .title(createBoardRequest.title())
            .build();
    }

    public static BoardGetResponse fromBoard(Board board) {
        if (board.getUser().getIsDeleted().equals(true)) {
            return BoardGetResponse.builder()
                .name("삭제된 유저입니다.")
                .title(board.getTitle())
                .content(board.getContent())
                .likes(board.getLikes())
                .createdAt(board.getCreatedAt())
                .build();
        }

        return BoardGetResponse.builder()
            .name(board.getUser().getName())
            .title(board.getTitle())
            .content(board.getContent())
            .likes(board.getLikes())
            .createdAt(board.getCreatedAt())
            .build();
    }

}
