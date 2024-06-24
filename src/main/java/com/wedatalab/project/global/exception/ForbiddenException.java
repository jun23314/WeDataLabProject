package com.wedatalab.project.global.exception;


public class ForbiddenException extends ApplicationException{
    public ForbiddenException(ErrorCode errorCode) {
        super(errorCode);
    }

}
