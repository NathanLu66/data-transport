package com.data.transport.model.request;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class ResetPasswordRequest {
    @NotNull
    private String userId;
    @NotNull
    private String oldPassword;
    @NotNull
    private String newPassword;
}
