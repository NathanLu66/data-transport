package com.data.transport.controller;

import com.data.transport.model.request.AuthLoginRequest;
import com.data.transport.model.response.ResponseEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    private static Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping(value = "/signIn")
    public ResponseEntity<String> signIn(@Valid @RequestBody AuthLoginRequest loginReq) throws Exception {
//        JsonResponse<SignInResponse> response = new JsonResponse<>();
//        signInRequest.setIpAddress(NetUtil.getIpAddress(servletRequest));
//        if (signInRequest.getUsername() != null) {
//            signInRequest.setUsername(StringUtils.trimToEmpty(signInRequest.getUsername()));
//        }
//        response.setData(uafAuthentication.signIn(signInRequest));
//        response.setStatus(HttpStatus.OK);
//        return response;
        return ResponseEntity.success("");
    }
}
