package com.wedatalab.project.board.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.wedatalab.project.domain.Board.dto.request.CreateBoardRequest;
import com.wedatalab.project.domain.Board.entity.Board;
import com.wedatalab.project.domain.Board.repository.BoardRepository;
import com.wedatalab.project.domain.Board.service.BoardService;
import com.wedatalab.project.domain.User.entity.User;
import com.wedatalab.project.domain.User.exception.UserNotFoundException;
import com.wedatalab.project.domain.User.repository.UserRepository;
import com.wedatalab.project.global.exception.ErrorCode;
import java.util.Optional;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@ExtendWith(MockitoExtension.class)
public class CreateBoardServiceTest {

    @InjectMocks
    private BoardService boardService;

    @Mock
    private BoardRepository boardRepository;

    @Mock
    private UserRepository userRepository;

    @Nested
    @DisplayName("board()는")
    class Context_boardCreate {

        @Test
        @DisplayName("게시판 정보를 저장할 수 있다.")
        void _willSuccess() {
            Long userId = 1L;

            //given
            CreateBoardRequest createBoardRequest = CreateBoardRequest.builder()
                .title("게시판 테스트")
                .content("어떤 내용을 넣지?")
                .build();

            User user = User.builder().id(userId).name("이름").email("이메일").build();
            Board board = Board.builder()
                .id(1L)
                .user(user)
                .title("게시판 테스트")
                .content("어떤 내용을 넣지?")
                .build();

            given(userRepository.findByIdAndIsDeletedIsFalse(userId)).willReturn(Optional.of(user));
            given(boardRepository.save(any(Board.class))).willReturn(board);

            // when
            boardService.createBoard(createBoardRequest, userId);

            // then
            verify(boardRepository, times(1)).save(any(Board.class));
        }

        @Test
        @DisplayName("회원 정보를 조회할 수 없으면 게시판 정보를 저장할 수 없다.")
        void userNotFound_willFail() {
            CreateBoardRequest createBoardRequest = CreateBoardRequest.builder()
                .title("게시판 테스트")
                .content("어떤 내용을 넣지?")
                .build();

            //when, then
            Throwable exception = assertThrows(UserNotFoundException.class, () -> {
                boardService.createBoard(createBoardRequest, 1L);
            });

            assertEquals(ErrorCode.USER_NOT_FOUND.getSimpleMessage(), exception.getMessage());
        }


    }
}
