package com.jt.web.service;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;

public interface UserService {

    /**
     * 登陆
     * @param user
     * @return
     */
    SysResult doRegister(User user);
}
