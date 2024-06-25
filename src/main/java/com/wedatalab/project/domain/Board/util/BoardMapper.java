package com.wedatalab.project.domain.Board.util;

import com.wedatalab.project.domain.Board.dto.request.BoardUpdateRequest;
import com.wedatalab.project.domain.Board.dto.request.CreateBoardRequest;
import com.wedatalab.project.domain.Board.entity.Board;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class BoardMapper {

    public static Board toBoard(CreateBoardRequest createBoardRequest) {
        return Board.builder()
            .content(createBoardRequest.content())
            .title(createBoardRequest.title())
            .build();
    }

    public static Board toBoardUpdate(BoardUpdateRequest boardUpdateRequest) {
        return Board.builder()
            .title(boardUpdateRequest.title())
            .content(boardUpdateRequest.content())
            .build();
    }

}
