package com.data.transport.controller;

import com.data.transport.common.constant.ResponseCode;
import com.data.transport.common.exception.BusinessException;
import com.data.transport.model.request.AuthLoginRequest;
import com.data.transport.model.request.ForgetPwdRequest;
import com.data.transport.model.request.ResetPasswordRequest;
import com.data.transport.model.response.ResponseEntity;
import com.data.transport.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {
    @Autowired
    private UserService userService;
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);
    @PostMapping(value = "/login")
    public ResponseEntity<String> signIn(@Valid @RequestBody AuthLoginRequest req, HttpServletRequest servletRequest) throws Exception {
        String resp = "";
        try{
            resp = userService.login(req);
            if(ObjectUtils.isEmpty(resp)){
                return ResponseEntity.error(ResponseCode.AUTHENTICATION_FAILED);
            }
            servletRequest.getSession().setAttribute("userSession", req.getUserName());
        }catch (BusinessException e){
            logger.error("User Login Failed , Error : {}" ,e.getErrorCode().getMessage());
            return ResponseEntity.error(e.getErrorCode());
        }catch (Exception e){
            logger.error("User Login Failed , Error : {}", e.getMessage());
            return ResponseEntity.error(ResponseCode.SYSTEM_ERROR);
        }
        return ResponseEntity.success(resp);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(HttpServletRequest servletRequest){
        servletRequest.getSession().removeAttribute("userSession");
        return ResponseEntity.success("");
    }

    @PostMapping(value = "/register")
    public ResponseEntity<Integer> register(@Valid @RequestBody AuthLoginRequest req) throws Exception {
        int resp = 0;
        try{
            resp = userService.register(req);
        }catch (BusinessException e){
            logger.error("User Registration Failed , Error : {}" ,e.getErrorCode().getMessage());
            return ResponseEntity.error(e.getErrorCode());
        }catch (Exception e){
            logger.error("User Registration Failed , Error : {}", e.getMessage());
            return ResponseEntity.error(ResponseCode.SYSTEM_ERROR);
        }
        return ResponseEntity.success(resp);
    }

    @PostMapping(value = "/updatePassword")
    public ResponseEntity<Integer> updatePassword(@Valid @RequestBody ResetPasswordRequest req) throws Exception {
        int resp = 0;
        try{
            resp = userService.resetPassword(req);
        }catch (BusinessException e){
            logger.error("Update Password Failed, UserId:{}, Error : {}" ,req.getUserId(), e.getErrorCode().getMessage());
            return ResponseEntity.error(e.getErrorCode());
        }catch (Exception e){
            logger.error("Update Password Failed , UserId:{}, Error : {}", req.getUserId(), e.getMessage());
            return ResponseEntity.error(ResponseCode.SYSTEM_ERROR);
        }
        return ResponseEntity.success(resp);
    }


    @PostMapping(value = "/forgetPassword")
    public ResponseEntity<Integer> forgetPassword(@Valid @RequestBody ForgetPwdRequest req) throws Exception {
        int resp = 0;
        try{
            resp = userService.forgetPassword(req);
        }catch (BusinessException e){
            logger.error("User Registration Failed , Error : {}" ,e.getErrorCode().getMessage());
            return ResponseEntity.error(e.getErrorCode());
        }catch (Exception e){
            logger.error("User Registration Failed , Error : {}", e.getMessage());
            return ResponseEntity.error(ResponseCode.SYSTEM_ERROR);
        }
        return ResponseEntity.success(resp);
    }
}
