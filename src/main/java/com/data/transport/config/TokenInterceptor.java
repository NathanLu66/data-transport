package com.data.transport.config;

import com.auth0.jwt.JWT;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.Claim;
import com.data.transport.common.constant.ResponseCode;
import com.data.transport.common.exception.BusinessException;
import com.data.transport.domain.User;
import com.data.transport.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.ObjectUtils;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.data.transport.common.util.JwtUtil;
import org.springframework.web.servlet.ModelAndView;

import java.util.Date;
import java.util.Map;

@Component
public class TokenInterceptor extends HandlerInterceptorAdapter {
    @Autowired
    private UserService userService;
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        if(request.getMethod().equals("OPTIONS")){
            response.setStatus(HttpServletResponse.SC_OK);
            return true;
        }
        //获取到token
        String token = request.getHeader("authorization");

        if (ObjectUtils.isEmpty(token)) {
            throw new BusinessException(ResponseCode.AUTHENTICATION_FAILED);
        }

        Date expiresAt = JWT.decode(token).getExpiresAt();
        if (expiresAt.before(new Date())){
            throw new BusinessException(ResponseCode.AUTHENTICATION_FAILED);
        }

        Map<String, Claim> result= JwtUtil.verifyToken(token);
        Integer userId;
        try {
            userId = JWT.decode(token).getClaim("id").asInt();
            if(userId == null){
                throw new BusinessException(ResponseCode.AUTHENTICATION_FAILED);
            }
        } catch (JWTDecodeException j) {
            throw new BusinessException(ResponseCode.AUTHENTICATION_FAILED);
        }

        User user = userService.getUserById(userId);
        if (user == null) {
            throw new BusinessException(ResponseCode.AUTHENTICATION_FAILED);
        }
        return true;
    }
}
