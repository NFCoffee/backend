package org.inu.nfcoffee.exception;

import org.inu.nfcoffee.common.ErrorCode;

public class EntityNotFoundException extends BusinessException {

    public EntityNotFoundException(ErrorCode errorCode) {
        super(errorCode);
    }

}
