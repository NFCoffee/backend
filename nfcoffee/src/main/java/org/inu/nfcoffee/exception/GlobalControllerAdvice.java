package org.inu.nfcoffee.exception;

import org.inu.nfcoffee.common.CommonResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.inu.nfcoffee.common.ErrorCode.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<CommonResponse<?>> handleDuplicateSignException(BusinessException e) {
        return ResponseEntity.badRequest().body(CommonResponse.from(e.getErrorCode(), null));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<CommonResponse<?>> handleException(Exception e) {
        return ResponseEntity.badRequest().body(CommonResponse.from(INTERNAL_SERVER_ERROR, null));
    }
}
