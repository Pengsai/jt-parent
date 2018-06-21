package com.jt.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author PS
 * @Date 2018/6/21 12:00
 **/
@Controller
@RequestMapping("/user")
public class UserController {


    @RequestMapping(value = "/login")
    public String login() {

        return "login";
    }

    @RequestMapping(value = "/register")
    public String register() {

        return "register";
    }

}
