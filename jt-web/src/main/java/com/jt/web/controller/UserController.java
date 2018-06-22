package com.jt.web.controller;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;
import com.jt.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author PS
 * @Date 2018/6/21 12:00
 **/
@Controller
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    public String login() {

        return "login";
    }

    @RequestMapping(value = "/register")
    public String register() {

        return "register";
    }

    @RequestMapping(value = "/doRegister")
    @ResponseBody
    public SysResult doRegister(User user) {

        SysResult sysResult = userService.doRegister(user);

        return sysResult;
    }



}
