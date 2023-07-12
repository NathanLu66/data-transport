package com.data.transport.service;

import com.data.transport.domain.User;
import com.data.transport.model.request.AuthLoginRequest;
import com.data.transport.model.request.ForgetPwdRequest;
import com.data.transport.model.request.ResetPasswordRequest;

public interface UserService {
    int register(AuthLoginRequest req);
    String login(AuthLoginRequest req);
    int resetPassword(ResetPasswordRequest req);
    int forgetPassword(ForgetPwdRequest req);
}
