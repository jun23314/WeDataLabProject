package com.wedatalab.project.domain.User.service;

import com.wedatalab.project.domain.Board.entity.Board;
import com.wedatalab.project.domain.Board.repository.BoardRepository;
import com.wedatalab.project.domain.Comment.exception.BoardNotFoundException;
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
import java.lang.reflect.Member;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final BoardRepository boardRepository;

    @Transactional
    public void createUser(UserCreateRequest userCreateRequest) {
        Optional<User> optionalUser = userRepository.findByEmail(userCreateRequest.email());
        if (optionalUser.isPresent()) {
            if(optionalUser.get().getIsDeleted().equals(true)){
                throw new MemberWhoWithdrewException(ErrorCode.MEMBER_WHO_WITHDREW);
            }
            throw new AlreadyExistUserException(ErrorCode.ALREADY_EXIST_USER);
        }

        User user = UserMapper.toUser(userCreateRequest);
        userRepository.save(user);
    }

    @Transactional
    public void updateUser(UserUpdateRequest userUpdateRequest) {
        String name = userUpdateRequest.name();
        Integer age = userUpdateRequest.age();
        String email = userUpdateRequest.email();

        User user = userRepository.findByIdAndIsDeletedIsFalse(userUpdateRequest.id()).orElseThrow(
            () -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND)
        );

        if (userRepository.existsByEmail(email)) {
            throw new AlreadyExistMailException(ErrorCode.ALREADY_EXIST_EMAIL);
        }

        user.updateUser(name, age, email);
        userRepository.save(user);
    }

    @Transactional
    public UserGetResponse getUser(Long userId) {
        User user = userRepository.findByIdAndIsDeletedIsFalse(userId).orElseThrow(
            () -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND)
        );
        return UserMapper.fromUser(user);
    }

    @Transactional
    public void deleteUser(Long userId) {
        User user = userRepository.findByIdAndIsDeletedIsFalse(userId).orElseThrow(
            () -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND)
        );
        user.deleteUser();
    }

    @Transactional
    public void getLikes(Long userId, Long boardId){
        User user = userRepository.findByIdAndIsDeletedIsFalse(userId).orElseThrow(
            () -> new UserNotFoundException(ErrorCode.USER_NOT_FOUND)
        );

        Board board = boardRepository.findByIdAndIsDeletedIsFalse(boardId).orElseThrow(
            () -> new BoardNotFoundException(ErrorCode.BOARD_NOT_FOUND)
        );

        board.updateUsers(user);
        boardRepository.save(board);


    }

}
