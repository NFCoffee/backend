package org.inu.nfcoffee.exception;

import org.inu.nfcoffee.common.ErrorCode;

public class AuthCodeExpiredException extends BusinessException {
    public AuthCodeExpiredException(ErrorCode errorCode) {
        super(errorCode);
    }
}
