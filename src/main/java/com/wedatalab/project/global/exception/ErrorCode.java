package com.wedatalab.project.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {
    //User
    USER_NOT_FOUND(HttpStatus.BAD_REQUEST, "회원을 찾을 수 없습니다."),
    ALREADY_EXIST_USER(HttpStatus.BAD_REQUEST, "이미 존재하는 회원입니다."),
    ALREADY_EXIST_NICKNAME(HttpStatus.BAD_REQUEST, "이미 존재하는 닉네임입니다"),

    //Board
    BOARD_NOT_FOUND(HttpStatus.BAD_REQUEST, "보드를 찾을 수 없습니다."),

    //comment
    COMMENT_NOT_FOUND(HttpStatus.BAD_REQUEST, "comment를 찾을 수 없습니다.")
    ;

    private final HttpStatus httpStatus;
    private final String simpleMessage;

    ErrorCode(HttpStatus httpStatus, String simpleMessage) {
        this.httpStatus = httpStatus;
        this.simpleMessage = simpleMessage;
    }

}
