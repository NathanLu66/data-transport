package com.data.transport.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ForgetPwdRequest {
    @NotNull
    private String userName;
}
