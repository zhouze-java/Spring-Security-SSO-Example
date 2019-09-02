package com.security.sso.server.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author 周泽
 * @date Create in 17:27 2019/8/31
 * @Description Security 配置类
 */
@Configuration
public class MyWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .formLogin()
                .and()
                // 所有的请求都必须授权后才能访问
                .authorizeRequests()
                .anyRequest()
                .authenticated();

    }


}
