package com.data.transport.service.impl;

import com.data.transport.common.constant.ResponseCode;
import com.data.transport.common.constant.UserRoleEnum;
import com.data.transport.common.exception.BusinessException;
import com.data.transport.common.util.IdGenerator;
import com.data.transport.common.util.JwtUtil;
import com.data.transport.domain.User;
import com.data.transport.mapper.UserMapper;
import com.data.transport.model.request.AuthLoginRequest;
import com.data.transport.model.request.ForgetPwdRequest;
import com.data.transport.model.request.ResetPasswordRequest;
import com.data.transport.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import org.springframework.util.ObjectUtils;

import javax.annotation.Resource;
import java.util.Date;
import java.util.UUID;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    private static Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
    @Override
    public int register(AuthLoginRequest req) {
        User oldUser = userMapper.selectByUsername(req.getUserName());
        if (oldUser!=null){
            throw new BusinessException(ResponseCode.DUPLICATE_USER);
        }
        User newUser = new User();
        String userPassword = req.getPassword();
        String salt= UUID.randomUUID().toString().toUpperCase();
        String uid = IdGenerator.nextUid();
        newUser.setSalt(salt);
        newUser.setUid(uid);
        newUser.setUsername(req.getUserName());
        newUser.setRole(UserRoleEnum.USER.getName());
        newUser.setIsDelete(0);
        newUser.setModifiedTime(new Date());
        newUser.setCreatedTime(new Date());
        newUser.setCreatedBy(uid);
        newUser.setModifiedBy(uid);
        String md5Password = getMd5Password(userPassword, salt);
        logger.debug("Slat Password : {}",md5Password);
        newUser.setPassword(md5Password);
        int result = userMapper.insert(newUser);
        if(result != 1){
            logger.error("Add user failed, username :{}", req.getUserName());
            throw new BusinessException(ResponseCode.REGISTRATION_FAILED);
        }
        return result;
    }

    @Override
    public String login(AuthLoginRequest req) {
        User user = userMapper.selectByUsername(req.getUserName());
        if (ObjectUtils.isEmpty(user)){
            throw new BusinessException(ResponseCode.INVALID_USER);
        }
        String salt = user.getSalt();
        String md5Password = getMd5Password(req.getPassword(), salt);
        if(!md5Password.equals(user.getPassword())){
            throw new BusinessException(ResponseCode.INVALID_PASSWORD);
        }
        return JwtUtil.createToken(user);
    }

    @Override
    public int resetPassword(ResetPasswordRequest req) {
        User user = userMapper.selectByUserId(req.getUserId());
        if (ObjectUtils.isEmpty(user)){
            throw new BusinessException(ResponseCode.INVALID_USER);
        }
        String salt = user.getSalt();
        String md5Password = getMd5Password(req.getOldPassword(), salt);
        if(!md5Password.equals(user.getPassword())){
            throw new BusinessException(ResponseCode.INVALID_PASSWORD);
        }

        salt= UUID.randomUUID().toString().toUpperCase();
        user.setSalt(salt);
        user.setPassword(getMd5Password(req.getNewPassword(), salt));
        user.setModifiedTime(new Date());
        int result = userMapper.updateByPrimaryKeySelective(user);
        if(result != 1){
            logger.error("Update user password failed, uid :{}", req.getUserId());
            throw new BusinessException(ResponseCode.UPDATE_PASSWORD_FAILED);
        }
        return result;
    }

    @Override
    public int forgetPassword(ForgetPwdRequest req) {
        User user = userMapper.selectByUsername(req.getUserName());
        if (ObjectUtils.isEmpty(user)){
            throw new BusinessException(ResponseCode.INVALID_USER);
        }
        String salt= UUID.randomUUID().toString().toUpperCase();
        // TODO 生成新密码 并发邮件
        String newPassword = "";
        user.setSalt(salt);
        user.setPassword(getMd5Password(newPassword, salt));
        user.setModifiedTime(new Date());
        int result = userMapper.updateByPrimaryKeySelective(user);
        if(result != 1){
            logger.error("Update user password failed");
            throw new BusinessException(ResponseCode.UPDATE_PASSWORD_FAILED);
        }
        return result;
    }

    @Override
    public User getUserById(int id) {
        User user = userMapper.selectByPrimaryKey(id);
        if (ObjectUtils.isEmpty(user)){
            throw new BusinessException(ResponseCode.INVALID_USER);
        }
        return user;
    }


    private String getMd5Password(String password,String salt){
        for (int i = 0; i < 3; i++) {
            password = DigestUtils.md5DigestAsHex((salt + password + salt).getBytes()).toUpperCase();
        }
        return password;
    }
}
