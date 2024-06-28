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
        return new Board(createBoardRequest.title(), createBoardRequest.content(), user);
    }

    public static BoardGetResponse fromBoard(Board board) {
        return new BoardGetResponse(board.getTitle(), board.getContent(), board.getUser().getName(),
            board.getUsers().size(), board.getCreatedAt());

    }

}
