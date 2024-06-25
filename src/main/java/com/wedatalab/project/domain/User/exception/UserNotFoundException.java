package com.wedatalab.project.domain.User.exception;

import com.wedatalab.project.global.exception.ApplicationException;
import com.wedatalab.project.global.exception.ErrorCode;

public class UserNotFoundException extends ApplicationException {

    public UserNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
