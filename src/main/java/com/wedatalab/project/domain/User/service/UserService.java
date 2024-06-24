package com.wedatalab.project.domain.User.service;

import com.wedatalab.project.domain.User.dto.request.UserCreateRequest;
import com.wedatalab.project.domain.User.entity.User;
import com.wedatalab.project.domain.User.exception.AlreadyExistUserException;
import com.wedatalab.project.domain.User.repository.UserRepository;
import com.wedatalab.project.domain.User.util.UserMapper;
import com.wedatalab.project.global.exception.ErrorCode;
import java.util.Optional;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public void createUser(UserCreateRequest userCreateRequest){
        Optional<User> optionalUser = userRepository.findByUserId(userCreateRequest.user_id());
        if(optionalUser.isPresent()) throw new AlreadyExistUserException(ErrorCode.ALREADY_EXIST_USER);

        User user = UserMapper.toUser(userCreateRequest);
        userRepository.save(user);
    }

}
