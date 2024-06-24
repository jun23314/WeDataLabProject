package com.wedatalab.project.domain.User.exception;

import com.wedatalab.project.global.exception.ApplicationException;
import com.wedatalab.project.global.exception.ErrorCode;

public class AlreadyExistUserException extends ApplicationException {

    public AlreadyExistUserException(ErrorCode errorCode) {
        super(errorCode);
    }
}
