package com.data.transport.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public enum ResponseCode {
    SUCCESS(0, "success"),
    PARAM_IS_INVALID(1001, "PARAM IS INVALID"),
    PARAM_IS_BLANK(1002, "PARAM IS BLANK"),
    AUTHENTICATION_FAILED(1003, "Authentication failed"),
    EMPTY_DATA(1004, "Empty Data"),
    SYSTEM_ERROR(4001, "SYSTEM ERROR");
    private int code;
    private String message;
}