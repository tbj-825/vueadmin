package com.tbj.security;

import cn.hutool.json.JSONUtil;
import com.tbj.entity.Result;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Component
public class LoginFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        ServletOutputStream outputStream = response.getOutputStream();
        Result result = new Result();
        result.setCode(400);
        result.setMsg(e.getMessage());
        outputStream.write(JSONUtil.toJsonStr(result).getBytes());
        outputStream.flush();
        outputStream.close();

    }
}
