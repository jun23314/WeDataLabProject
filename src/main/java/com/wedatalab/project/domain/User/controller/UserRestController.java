package com.wedatalab.project.domain.User.controller;

import com.wedatalab.project.domain.User.dto.request.UserCreateRequest;
import com.wedatalab.project.domain.User.dto.request.UserUpdateRequest;
import com.wedatalab.project.domain.User.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("v1/api/user")
@RequiredArgsConstructor
public class UserRestController {
    private final UserService userService;

    @PostMapping("/create")
    public ResponseEntity<String> createUser(
        @RequestBody UserCreateRequest userCreateRequest
    ){
        userService.createUser(userCreateRequest);
        return ResponseEntity.ok("유저 생성을 완료하였습니다.");
    }

    @PutMapping("/update")
    public ResponseEntity<String> updateUser(
        @RequestBody UserUpdateRequest userUpdateRequest
    ){
        userService.updateUser(userUpdateRequest);
        return ResponseEntity.ok("유저 업데이트를 완료하였습니다.");
    }


}
