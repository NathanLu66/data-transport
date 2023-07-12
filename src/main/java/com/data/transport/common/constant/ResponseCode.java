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
    DUPLICATE_USER(1005, "Duplicate User"),
    REGISTRATION_FAILED(1006, "User Registration Failed"),
    INVALID_USER(1007, "Invalid User"),
    INVALID_PASSWORD(1008, "Invalid Password"),
    UPDATE_PASSWORD_FAILED(1009, "Update Password Failed"),
    SYSTEM_ERROR(4001, "SYSTEM ERROR");
    private int code;
    private String message;
}