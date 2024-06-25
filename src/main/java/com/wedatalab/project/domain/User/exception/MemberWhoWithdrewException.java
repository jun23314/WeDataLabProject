package com.wedatalab.project.domain.User.exception;

import com.wedatalab.project.global.exception.ApplicationException;
import com.wedatalab.project.global.exception.ErrorCode;

public class MemberWhoWithdrewException extends ApplicationException {

    public MemberWhoWithdrewException(ErrorCode errorCode) {
        super(errorCode);
    }
}
