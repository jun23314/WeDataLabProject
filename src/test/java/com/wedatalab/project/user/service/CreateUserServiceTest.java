package com.wedatalab.project.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.wedatalab.project.domain.Board.dto.request.CreateBoardRequest;
import com.wedatalab.project.domain.User.dto.request.UserCreateRequest;
import com.wedatalab.project.domain.User.entity.User;
import com.wedatalab.project.domain.User.exception.AlreadyExistUserException;
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
public class CreateUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Nested
    @DisplayName("user()는")
    class Context_userCreate {

        @Test
        @DisplayName("유저 정보를 생성할 수 있다.")
        void _willSuccess() {
            //given
            UserCreateRequest userCreateRequest = new UserCreateRequest("email", "name", 0);
            User user = new User("name", 0, "email");

            given(userRepository.save(any(User.class))).willReturn(user);

            // when
            userService.createUser(userCreateRequest);

            // then
            verify(userRepository, times(1)).save(any(User.class));
        }

        @Test
        @DisplayName("이미 가입된 유저는 생성할 수 없다.")
        void userAlreadyExist_willFail() {
            //given
            UserCreateRequest userCreateRequest = new UserCreateRequest("email", "name", 0);
            User user = new User("name", 0, "email");

            given(userRepository.findByEmail("email")).willReturn(Optional.of(user));

            //when, then
            Throwable exception = assertThrows(AlreadyExistUserException.class, () -> {
                userService.createUser(userCreateRequest);
            });

            assertEquals(ErrorCode.ALREADY_EXIST_USER.getSimpleMessage(), exception.getMessage());
        }



    }
}
