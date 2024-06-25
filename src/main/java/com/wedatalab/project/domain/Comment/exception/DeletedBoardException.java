package com.wedatalab.project.domain.Comment.exception;

import com.wedatalab.project.global.exception.ApplicationException;
import com.wedatalab.project.global.exception.ErrorCode;

public class DeletedBoardException extends ApplicationException {

    public DeletedBoardException(ErrorCode errorCode) {
        super(errorCode);
    }
}
