package com.wedatalab.project.global.exception;

public class InternalServerException extends ApplicationException{
    public InternalServerException(ErrorCode errorCode) {
        super(errorCode);
    }

}
