package com.data.transport.common.exception;

import com.data.transport.common.constant.ResponseCode;

public class BusinessException extends RuntimeException {
    private final ResponseCode errorCode;
    public BusinessException(ResponseCode errorCode) {
        super();
        this.errorCode = errorCode;
    }
    public ResponseCode getErrorCode() {
        return errorCode;
    }
}
