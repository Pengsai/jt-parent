package com.jt.web.controller;

import com.google.common.base.Strings;
import com.jt.common.util.CookieUtils;
import com.jt.common.util.RedisUtils;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;
import com.jt.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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

    @Qualifier("redisPool")
    @Autowired
    private RedisUtils redisUtils;

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

    @RequestMapping(value = "/doLogin")
    @ResponseBody
    public SysResult doLogin(String username, String password,
                             HttpServletRequest request, HttpServletResponse response) {

        if (Strings.isNullOrEmpty(username) || Strings.isNullOrEmpty(password)) {
            return SysResult.build(201, "用户名和密码不能为空");
        }

        SysResult sysResult = userService.doLogin(username, password);

        String ticket = (String) sysResult.getData();

        CookieUtils.setCookie(request, response, "JT_TICKET", ticket);

        return sysResult;
    }

    //用户退出
    @RequestMapping("/logout")
    public String logout(HttpServletRequest request,HttpServletResponse response){

        String cookieName = "JT_TICKET";

        //将数据从redis中删除
        String ticket = CookieUtils.getCookieValue(request, cookieName);
        System.out.println(ticket);

        redisUtils.del(ticket);

        //应该将Cookie删除
        CookieUtils.deleteCookie(request, response, cookieName);

        return "redirect:/index.html"; //返回值节刚过应该是伪静态的
    }

}
