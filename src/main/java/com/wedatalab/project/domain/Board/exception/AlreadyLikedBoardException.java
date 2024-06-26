package com.wedatalab.project.domain.Board.exception;

import com.wedatalab.project.global.exception.ApplicationException;
import com.wedatalab.project.global.exception.ErrorCode;

public class AlreadyLikedBoardException extends ApplicationException {

    public AlreadyLikedBoardException(ErrorCode errorCode) {
        super(errorCode);
    }
}
