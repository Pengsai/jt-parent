package com.jt.sso.controller;

import com.google.common.base.Strings;
import com.jt.common.vo.SysResult;
import com.jt.sso.pojo.User;
import com.jt.sso.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author PS
 * @Date 2018/6/21 14:30
 **/
@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/check/{param}/{type}")
    @ResponseBody
    public Object findCheckUser(@PathVariable String param, @PathVariable int type,
                                String callback) {

        try {
            param = URLDecoder.decode(param, "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        boolean checkResult = userService.findCheckUser(param, type);

        SysResult sysResult = SysResult.oK(checkResult);

        MappingJacksonValue jacksonValue = new MappingJacksonValue(sysResult);

        jacksonValue.setJsonpFunction(callback);
        return jacksonValue;
    }

    @RequestMapping("/register")
    @ResponseBody
    public SysResult register(User user) {
        try {
            String username = userService.saveUser(user);
            return SysResult.oK(username);
        } catch (Exception e) {
            return SysResult.build(201, "用户新增失败");
        }
    }

    @RequestMapping("/login")
    @ResponseBody
    public SysResult login(@RequestParam("u") String username, @RequestParam("p") String password) {
        String ticket = userService.findLogin(username, password);

        if (!Strings.isNullOrEmpty(ticket)) {
            return SysResult.oK(ticket);
        } else {
            return SysResult.build(201, "用户名或密码错误");
        }
    }

    @RequestMapping("/query/{ticket}")
    @ResponseBody
    public Object findUserByTicket(@PathVariable String ticket, String callback) {

        String userJSON = userService.findUserByTicket(ticket);

        if (Strings.isNullOrEmpty(userJSON)) {
            return SysResult.build(201, "根据ticket没有查到用户信息");
        } else {
            SysResult sysResult = SysResult.oK(userJSON);
            MappingJacksonValue jacksonValue = new MappingJacksonValue(sysResult);
            jacksonValue.setJsonpFunction(callback);

            return jacksonValue;
        }
    }


}
