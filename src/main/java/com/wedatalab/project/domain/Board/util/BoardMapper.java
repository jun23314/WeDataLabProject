package com.wedatalab.project.domain.Board.util;

import com.wedatalab.project.domain.Board.dto.request.BoardUpdateRequest;
import com.wedatalab.project.domain.Board.dto.request.CreateBoardRequest;
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

}
