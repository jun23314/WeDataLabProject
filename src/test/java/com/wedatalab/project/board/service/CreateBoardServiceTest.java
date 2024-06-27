package com.wedatalab.project.board.service;

import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.junit.jupiter.api.Assertions.*;



import com.wedatalab.project.domain.Board.dto.request.CreateBoardRequest;
import com.wedatalab.project.domain.Board.entity.Board;
import com.wedatalab.project.domain.Board.repository.BoardRepository;
import com.wedatalab.project.domain.Board.service.BoardService;
import com.wedatalab.project.domain.User.entity.User;
import com.wedatalab.project.domain.User.exception.UserNotFoundException;
import com.wedatalab.project.domain.User.repository.UserRepository;
import com.wedatalab.project.domain.User.service.UserService;
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

    @Mock
    private UserService userService;

    @Nested
    @DisplayName("board()는")
    class Context_postTrip {

        @Test
        @DisplayName("게시판 정보를 저장할 수 있다.")
        void _willSuccess() {
            //given
            CreateBoardRequest createBoardRequest = CreateBoardRequest.builder()
                .title("게시판 테스트")
                .content("어떤 내용을 넣지?")
                .build();

            User user = User.builder().id(1L).name("이름").email("이메일").build();
            Board board = Board.builder()
                .id(1L)
                .user(user)
                .title("게시판 테스트")
                .content("어떤 내용을 넣지?")
                .build();

            given(userRepository.findByIdAndIsDeletedIsFalse(1L)).willReturn(Optional.of(user));
            given(boardRepository.save(any(Board.class))).willReturn(board);

            // when
            boardService.createBoard(createBoardRequest, 1L);

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

            given(userService.getUser(any(Long.TYPE))).willThrow(
                new UserNotFoundException(ErrorCode.USER_NOT_FOUND)
            );

            //when, then
            Throwable exception = assertThrows(UserNotFoundException.class, () -> {
                boardService.createBoard(createBoardRequest, 1L);
            });

            assertEquals(ErrorCode.USER_NOT_FOUND.getSimpleMessage(), exception.getMessage());
        }




    }
}
