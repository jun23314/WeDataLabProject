package com.wedatalab.project.domain.Comment.exception;

import com.wedatalab.project.global.exception.ApplicationException;
import com.wedatalab.project.global.exception.ErrorCode;

public class BoardNotFoundException extends ApplicationException {

    public BoardNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }
}
