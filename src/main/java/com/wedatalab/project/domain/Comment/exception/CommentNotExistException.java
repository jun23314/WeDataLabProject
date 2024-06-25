package com.wedatalab.project.domain.Comment.exception;

import com.wedatalab.project.global.exception.ApplicationException;
import com.wedatalab.project.global.exception.ErrorCode;

public class CommentNotExistException extends ApplicationException {

    public CommentNotExistException(ErrorCode errorCode) {
        super(errorCode);
    }
}
