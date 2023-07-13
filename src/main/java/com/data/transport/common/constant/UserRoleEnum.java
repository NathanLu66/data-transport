package com.data.transport.common.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@ToString
public enum UserRoleEnum {
    USER("user"),
    ADMIN ("admin"),
    VISITOR("visitor");
    private final String name;
}
