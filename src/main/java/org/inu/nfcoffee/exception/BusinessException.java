package org.inu.nfcoffee.exception;

import lombok.Getter;
import org.inu.nfcoffee.common.ErrorCode;

@Getter
public class BusinessException extends RuntimeException {
    private final ErrorCode errorCode;

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.description());
        this.errorCode = errorCode;
    }
}
