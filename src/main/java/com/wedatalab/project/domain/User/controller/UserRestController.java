package com.wedatalab.project.domain.User.controller;

import com.wedatalab.project.domain.User.dto.request.UserCreateRequest;
import com.wedatalab.project.domain.User.dto.request.UserUpdateRequest;
import com.wedatalab.project.domain.User.dto.response.UserGetResponse;
import com.wedatalab.project.domain.User.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
@RequiredArgsConstructor
@Tag(name = "user api", description = "user 관련 API")
public class UserRestController {

    private final UserService userService;

    @PostMapping("")
    @Operation(summary = "user 정보 저장 API", description = "user 정보를 DB에 저장하는 API입니다.")
    public ResponseEntity<String> createUser(
        @RequestBody UserCreateRequest userCreateRequest
    ) {
        userService.createUser(userCreateRequest);
        return new ResponseEntity<>("유저 생성을 완료하였습니다.", HttpStatus.OK);
    }

    @PutMapping("/{user-id}")
    @Operation(summary = "user 정보 update API", description = "user 정보를 update하는 API입니다.")
    public ResponseEntity<String> updateUser(
        @PathVariable("user-id") Long userId,
        @RequestBody UserUpdateRequest userUpdateRequest
    ) {
        userService.updateUser(userUpdateRequest, userId);
        return ResponseEntity.ok("유저 업데이트를 완료하였습니다.");
    }

    @GetMapping("/{user-id}")
    @Operation(summary = "user 정보 get API", description = "user 정보를 DB에서 가져오는 API입니다.")
    public ResponseEntity<UserGetResponse> getUser(
        @PathVariable("user-id") Long userId
    ) {
        UserGetResponse userGetResponse = userService.getUser(userId);
        return ResponseEntity.ok(userGetResponse);
    }

    @DeleteMapping("/{user-id}")
    @Operation(summary = "user 삭제 API", description = "user 정보를 삭제하는 API입니다.")
    public ResponseEntity<String> deleteUser(
        @PathVariable("user-id") Long userId
    ) {
        userService.deleteUser(userId);
        return ResponseEntity.ok("user를 성공적으로 삭제했습니다.");
    }
}
