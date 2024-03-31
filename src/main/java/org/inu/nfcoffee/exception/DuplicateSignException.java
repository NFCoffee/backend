package org.inu.nfcoffee.exception;

import org.inu.nfcoffee.common.ErrorCode;

public class DuplicateSignException extends RuntimeException {
    public DuplicateSignException(ErrorCode errorCode) {
        super(errorCode.description());
    }
}
