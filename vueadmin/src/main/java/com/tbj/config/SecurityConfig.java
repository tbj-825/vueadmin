package com.tbj.config;

import com.tbj.security.CaptchaFilter;
import com.tbj.security.LoginFailureHandler;
import com.tbj.security.LoginSuccessHandler;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Resource
    private LoginFailureHandler loginFailureHandler;

    @Resource
    private LoginSuccessHandler loginSuccessHandler;

    @Resource
    private CaptchaFilter captchaFilter;


    private static final String[] URL = {
        "/login", "/logout", "/captcha"
    };

    @Override
    protected void configure(HttpSecurity http) throws Exception {


        http.cors().and().csrf().disable()
                .formLogin()
                .successHandler(loginSuccessHandler)
                .failureHandler(loginFailureHandler)
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers(URL).permitAll()
                .anyRequest().authenticated()
                .and()
                .addFilterBefore(captchaFilter, UsernamePasswordAuthenticationFilter.class);


    }
}
