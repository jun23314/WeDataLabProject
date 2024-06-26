package com.wedatalab.project.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    //User
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "회원을 찾을 수 없습니다."),
    ALREADY_EXIST_USER(HttpStatus.BAD_REQUEST, "이미 존재하는 회원입니다."),
    ALREADY_EXIST_EMAIL(HttpStatus.BAD_REQUEST, "이미 존재하는 메일입니다"),
    MEMBER_WHO_WITHDREW(HttpStatus.BAD_REQUEST, "탈퇴한 회원입니다."),

    //Board
    BOARD_NOT_FOUND(HttpStatus.BAD_REQUEST, "보드를 찾을 수 없습니다."),
    DELETED_BOARD(HttpStatus.BAD_REQUEST, "삭제된 보드입니다."),

    //comment
    COMMENT_NOT_FOUND(HttpStatus.BAD_REQUEST, "comment를 찾을 수 없습니다."),
    COMMENT_NOT_EXIST(HttpStatus.BAD_REQUEST, "존재하는 comment가 없습니다."),
    ALREADY_LIKED_COMMENT(HttpStatus.BAD_REQUEST, "이미 좋아요를 눌렀습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String simpleMessage;

    ErrorCode(HttpStatus httpStatus, String simpleMessage) {
        this.httpStatus = httpStatus;
        this.simpleMessage = simpleMessage;
    }

}
