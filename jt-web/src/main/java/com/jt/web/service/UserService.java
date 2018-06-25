package com.jt.web.service;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;

public interface UserService {

    /**
     * 注册
     * @param user
     * @return
     */
    SysResult doRegister(User user);

    /**
     * 登陆
     * @param username
     * @param password
     * @return
     */
    SysResult doLogin(String username, String password);
}
