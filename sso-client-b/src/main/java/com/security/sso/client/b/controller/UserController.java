package com.security.sso.client.b.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 周泽
 * @date Create in 10:25 2019/8/31
 * @Description
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/me")
    public Authentication me(Authentication authentication){
        return authentication;
    }

}
