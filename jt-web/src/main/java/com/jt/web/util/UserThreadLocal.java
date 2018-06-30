package com.jt.web.util;

import com.jt.web.pojo.User;

/**
 * @ClassName UserThreadLocal
 * @Description TODO
 * @Author PS
 * @Date 2018/6/26 15:14
 **/
public class UserThreadLocal {

    private static ThreadLocal<User> userThreadLocal = new ThreadLocal<>();

    public static User getUser() {
        return userThreadLocal.get();
    }

    public static  void setUser(User user) {
        userThreadLocal.set(user);
    }
}
