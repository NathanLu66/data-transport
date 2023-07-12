package com.data.transport.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class AuthLoginRequest {
    @NotNull
    private String userName;
    @NotNull
    private String password;
}
