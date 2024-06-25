package com.wedatalab.project.domain.User.exception;

import com.wedatalab.project.global.exception.ApplicationException;
import com.wedatalab.project.global.exception.ErrorCode;

public class AlreadyExistMailException extends ApplicationException {

    public AlreadyExistMailException(ErrorCode errorCode) {
        super(errorCode);
    }
}
