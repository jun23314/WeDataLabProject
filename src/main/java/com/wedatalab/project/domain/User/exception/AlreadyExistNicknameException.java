package com.wedatalab.project.domain.User.exception;

import com.wedatalab.project.global.exception.ApplicationException;
import com.wedatalab.project.global.exception.ErrorCode;

public class AlreadyExistNicknameException extends ApplicationException {

    public AlreadyExistNicknameException(ErrorCode errorCode) {
        super(errorCode);
    }
}
