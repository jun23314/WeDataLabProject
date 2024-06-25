package com.wedatalab.project.domain.User.service;

import com.wedatalab.project.domain.User.dto.request.UserCreateRequest;
import com.wedatalab.project.domain.User.dto.request.UserUpdateRequest;
import com.wedatalab.project.domain.User.entity.User;
import com.wedatalab.project.domain.User.exception.AlreadyExistNicknameException;
import com.wedatalab.project.domain.User.exception.AlreadyExistUserException;
import com.wedatalab.project.domain.User.exception.UserNotFoundException;
import com.wedatalab.project.domain.User.repository.UserRepository;
import com.wedatalab.project.domain.User.util.UserMapper;
import com.wedatalab.project.global.exception.ErrorCode;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public void createUser(UserCreateRequest userCreateRequest) {
        Optional<User> optionalUser = userRepository.findByNickname(userCreateRequest.nickname());
        if (optionalUser.isPresent()) {
            throw new AlreadyExistUserException(ErrorCode.ALREADY_EXIST_USER);
        }

        User user = UserMapper.toUser(userCreateRequest);
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(UserUpdateRequest userUpdateRequest) {
        User tmpUser = userRepository.findById(userUpdateRequest.id()).orElseThrow(
            () -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND)
        );

        String nickname = userUpdateRequest.nickname();
        if (userRepository.findByNickname(nickname).isPresent()) {
            throw new AlreadyExistNicknameException(ErrorCode.ALREADY_EXIST_NICKNAME);
        }

        User user = UserMapper.toUserUpdate(userUpdateRequest);

        userRepository.save(user);
    }

}
