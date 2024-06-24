package com.wedatalab.project.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<String> applicationException(ApplicationException e) {
        log.error("Application Exception:: ", e);
        return ResponseEntity.status(e.getErrorCode().getHttpStatus())
            .body(e.getMessage());

    }

}
