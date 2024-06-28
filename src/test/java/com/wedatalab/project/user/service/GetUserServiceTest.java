package com.wedatalab.project.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.wedatalab.project.domain.User.dto.request.UserUpdateRequest;
import com.wedatalab.project.domain.User.dto.response.UserGetResponse;
import com.wedatalab.project.domain.User.entity.User;
import com.wedatalab.project.domain.User.exception.AlreadyExistMailException;
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
public class GetUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Nested
    @DisplayName("user()는")
    class Context_userGet {

        @Test
        @DisplayName("유저 정보를 얻을 수 있다.")
        void _willSuccess() {
            //given
            Long userId = 1L;
            User user = new User("name", 0, "email");

            given(userRepository.findById(userId)).willReturn(Optional.of(user));

            //when, then
            when(userRepository.findById(userId)).thenReturn(Optional.of(user));
            UserGetResponse userGetResponse = userService.getUser(userId);

            assertEquals(userGetResponse.email(), user.getEmail());
            verify(userRepository).findById(userId);
        }

        @Test
        @DisplayName("유저가 존재하지 않는 경우 유저 정보를 얻을 수 없다")
        void userNotFound_willFail(){
            //given
            Long userId = 1L;
            User user = new User("name", 0, "email");

            //when, then
            Throwable exception = assertThrows(UserNotFoundException.class, () -> {
                userService.getUser(userId);
            });

            assertEquals(ErrorCode.USER_NOT_FOUND.getSimpleMessage(), exception.getMessage());
        }
    }
}
