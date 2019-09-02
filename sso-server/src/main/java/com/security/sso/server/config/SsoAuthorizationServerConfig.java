package com.security.sso.server.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

/**
 * @author 周泽
 * @date Create in 21:21 2019/8/30
 * @Description 认证服务器的配置类
 */
@Configuration
@EnableAuthorizationServer
public class SsoAuthorizationServerConfig extends AuthorizationServerConfigurerAdapter {

    private static final String DEFAULT_SIGN_KEY = "default-sign-key";

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints) throws Exception {
        endpoints.tokenStore(jwtTokenStore())
                .accessTokenConverter(jwtAccessTokenConverter());
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.inMemory()
                .withClient("testAppId1")
                .secret("testSecert1")
                .authorizedGrantTypes("authorization_code", "refresh_token", "password")
                .redirectUris("http://127.0.0.1:8080/client-a/login")
                .scopes("all")
                .and()
                .withClient("testAppId2")
                .secret("testSecert2")
                .authorizedGrantTypes("authorization_code", "refresh_token", "password")
                .redirectUris("http://127.0.0.1:8081/client-b/login")
                .scopes("all");
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security) throws Exception {
        // 表是在访问认证服务器的 tokenKey(就是 DEFAULT_SIGN_KEY ) 的时候,需要经过身份认证
        security.tokenKeyAccess("isAuthenticated()");
    }

    @Bean
    public TokenStore jwtTokenStore(){
        return new JwtTokenStore(jwtAccessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter jwtAccessTokenConverter(){
        // token生成中的一些处理
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        converter.setSigningKey(DEFAULT_SIGN_KEY);
        return converter;
    }

}
