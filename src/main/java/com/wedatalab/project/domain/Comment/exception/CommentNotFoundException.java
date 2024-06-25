package com.wedatalab.project.domain.Comment.exception;

import com.wedatalab.project.global.exception.ApplicationException;
import com.wedatalab.project.global.exception.ErrorCode;

public class CommentNotFoundException extends ApplicationException {

    public CommentNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
