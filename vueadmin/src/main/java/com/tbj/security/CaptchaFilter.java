package com.tbj.security;

import com.tbj.exception.CaptchaException;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CaptchaFilter extends OncePerRequestFilter {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Resource
    LoginFailureHandler loginFailureHandler;


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        String uri = request.getRequestURI();

        if ("/login".equals(uri) && request.getMethod().equals("POST")) {

            try {
                //验证码判断
                verifyCaptcha(request, response);
            } catch (AuthenticationException e) {
                loginFailureHandler.onAuthenticationFailure(request, response, e);
            }
        }

        filterChain.doFilter(request, response);

    }

    private void verifyCaptcha(HttpServletRequest request, HttpServletResponse response) {
        String key = request.getParameter("key");
        String code = request.getParameter("code");

        //判空
        if (StringUtils.isBlank(key) || StringUtils.isBlank(code)) {
            throw new CaptchaException("验证码错误");
        }

        //判断验证码是否一致
        String captcha = (String) redisTemplate.opsForValue().get(key);
        if (!code.equals(captcha)) {
            throw new CaptchaException("验证码错误");
        }

        //执行到此处，代表验证码是正确的
        redisTemplate.delete(key);
    }
}
