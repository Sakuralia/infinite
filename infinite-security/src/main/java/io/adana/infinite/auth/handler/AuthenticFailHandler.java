package io.adana.infinite.auth.handler;

import com.alibaba.fastjson.JSON;
import io.adana.infinite.auth.exception.ValidateException;
import io.adana.infinite.common.web.domain.enums.ResultCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author sakura
 * @version 1.0
 * @description
 * @date 2020/11/4 15:16
 */
@Slf4j
@Component
public class AuthenticFailHandler extends SimpleUrlAuthenticationFailureHandler {

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setStatus(HttpStatus.OK.value());
        response.setContentType("application/json;charset=UTF-8");
        log.error("errors in login,exceptions:{}", exception.getMessage());
        if (exception instanceof ValidateException) {
            response.getWriter().write(JSON.toJSONString(ResultCode.LOGIN_ERROR));
        } else if (exception instanceof UsernameNotFoundException) {
            response.getWriter().write(JSON.toJSONString(ResultCode.LOGIN_PARAMS_ERROR));
        } else if (exception instanceof BadCredentialsException) {

        }
    }
}
