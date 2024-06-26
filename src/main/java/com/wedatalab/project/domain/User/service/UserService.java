package com.wedatalab.project.domain.User.service;

import com.wedatalab.project.domain.User.dto.request.UserCreateRequest;
import com.wedatalab.project.domain.User.dto.request.UserUpdateRequest;
import com.wedatalab.project.domain.User.dto.response.UserGetResponse;
import com.wedatalab.project.domain.User.entity.User;
import com.wedatalab.project.domain.User.exception.AlreadyExistMailException;
import com.wedatalab.project.domain.User.exception.AlreadyExistUserException;
import com.wedatalab.project.domain.User.exception.MemberWhoWithdrewException;
import com.wedatalab.project.domain.User.exception.UserNotFoundException;
import com.wedatalab.project.domain.User.repository.UserRepository;
import com.wedatalab.project.domain.User.util.UserMapper;
import com.wedatalab.project.global.exception.ErrorCode;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;


    @Transactional
    public void createUser(UserCreateRequest userCreateRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(userCreateRequest.email());
        if (optionalUser.isPresent()) {
            throw new AlreadyExistUserException(ErrorCode.ALREADY_EXIST_USER);
        }

        User user = UserMapper.toUser(userCreateRequest);
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(UserUpdateRequest userUpdateRequest, Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
            () -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND)
        );

        if (!Objects.equals(userUpdateRequest.email(), user.getEmail())) {
            if (userRepository.existsByEmail(userUpdateRequest.email())) {
                throw new AlreadyExistMailException(ErrorCode.ALREADY_EXIST_EMAIL);
            }
        }

        user.updateUser(userUpdateRequest);
        userRepository.save(user);
    }

    @Transactional
    public UserGetResponse getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
            () -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND)
        );
        return UserMapper.fromUser(user);
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
            () -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND)
        );
        userRepository.delete(user);
    }
}
