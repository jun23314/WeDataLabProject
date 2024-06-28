package com.wedatalab.project.user.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.wedatalab.project.domain.User.dto.request.UserCreateRequest;
import com.wedatalab.project.domain.User.dto.request.UserUpdateRequest;
import com.wedatalab.project.domain.User.entity.User;
import com.wedatalab.project.domain.User.exception.AlreadyExistMailException;
import com.wedatalab.project.domain.User.exception.AlreadyExistUserException;
import com.wedatalab.project.domain.User.repository.UserRepository;
import com.wedatalab.project.domain.User.service.UserService;
import com.wedatalab.project.global.exception.ErrorCode;
import java.util.Objects;
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
public class UpdateUserServiceTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserService userService;

    @Nested
    @DisplayName("user()는")
    class Context_userUpdate {

        @Test
        @DisplayName("유저 정보를 수정할 수 있다. - 이메일과 이름 모두 바꾸는 경우")
        void _willSuccess() {
            //given
            Long userId = 1L;
            UserUpdateRequest userUpdateRequest = new UserUpdateRequest("updatedName", 0, "updatedEmail");
            User user = new User("name", 0, "email");

            given(userRepository.findById(userId)).willReturn(Optional.of(user));

            // when
            userService.updateUser(userUpdateRequest, userId);

            // then
            assertEquals("updatedName", user.getName());
            assertEquals("updatedEmail", user.getEmail());
        }

        @Test
        @DisplayName("유저 정보를 수정할 수 있다. - 이름만 바꾸는 경우")
        void onlyName_willSuccess(){
            //given
            Long userId = 1L;
            UserUpdateRequest userUpdateRequest = new UserUpdateRequest("updatedName", 0, "email");
            User user = new User("name", 0, "email");

            given(userRepository.findById(userId)).willReturn(Optional.of(user));

            //when
            userService.updateUser(userUpdateRequest, userId);

            //then
            assertEquals(userUpdateRequest.name(), user.getName());
            assertEquals(userUpdateRequest.email(), user.getEmail());
        }

        @Test
        @DisplayName("유저 정보를 수정할 수 있다. - 이메일만 바꾸는 경우")
        void onlyEmail_willSuccess(){
            //given
            Long userId = 1L;
            UserUpdateRequest userUpdateRequest = new UserUpdateRequest("name", 0, "updatedEmail");
            User user = new User("name", 0, "email");

            given(userRepository.findById(userId)).willReturn(Optional.of(user));

            //when
            userService.updateUser(userUpdateRequest, userId);

            //then
            assertEquals(userUpdateRequest.name(), user.getName());
            assertEquals(userUpdateRequest.email(), user.getEmail());
        }

        @Test
        @DisplayName("이미 존재하는 이메일로 수정할 수 없다.")
        void alreadyExistEmail_willFail(){
            //given
            Long userId = 1L;
            UserUpdateRequest userUpdateRequest = new UserUpdateRequest("name", 0, "updatedEmail");
            User user = new User("name", 0, "email");

            given(userRepository.findById(userId)).willReturn(Optional.of(user));
            given(userRepository.existsByEmail(userUpdateRequest.email())).willReturn(true);

            //when
            Throwable exception = assertThrows(AlreadyExistMailException.class, () ->{
                userService.updateUser(userUpdateRequest, userId);
            });

            //then
            assertEquals(ErrorCode.ALREADY_EXIST_EMAIL.getSimpleMessage(), exception.getMessage());
        }





    }
}
