package com.wedatalab.project.domain.User.exception;

import com.wedatalab.project.global.exception.ApplicationException;
import com.wedatalab.project.global.exception.ErrorCode;

public class AlreadyLikedCommentException extends ApplicationException {

    public AlreadyLikedCommentException(ErrorCode errorCode) {
        super(errorCode);
    }
}
