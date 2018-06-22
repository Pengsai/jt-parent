package com.jt.sso.service;

import com.jt.sso.pojo.User;

public interface UserService {

    /**
     * 检测用户是否存在
     * @param param
     * @param type
     * @return
     */
    boolean findCheckUser(String param, int type);

    /**
     * 新增用户，并返回用户名
     * @param user
     * @return
     */
    String saveUser(User user);
}
