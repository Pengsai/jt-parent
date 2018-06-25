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

    /**
     * 是否登陆，并返回票据
     * @param username
     * @param password
     * @return
     */
    String findLogin(String username, String password);

    /**
     * 根据ticket查询用户信息
     * @param ticket
     * @return
     */
    String findUserByTicket(String ticket);
}
