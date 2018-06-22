package com.jt.sso.service;

import com.jt.common.service.SysProperties;
import com.jt.sso.mapper.UserMapper;
import com.jt.sso.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.apache.commons.codec.digest.DigestUtils;

import java.util.Date;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author PS
 * @Date 2018/6/21 14:37
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Override
    public boolean findCheckUser(String param, int type) {
        String cloumn = null;
        switch (type) {
            case SysProperties.loginType.username:
                cloumn = "username";
                break;
            case SysProperties.loginType.phone:
                cloumn = "phone";
                break;
            case SysProperties.loginType.email:
                cloumn = "email";
                break;
        }
        int countNum = userMapper.findCheckUser(cloumn, param);
        return countNum > 0 ? true : false;
    }

    @Override
    public String saveUser(User user) {

        String md5Password = DigestUtils.md5Hex(user.getPassword());
        user.setPassword(md5Password);
        user.setEmail(user.getPhone()); //为了防止null数据 引入电话
        user.setCreated(new Date());
        user.setUpdated(user.getCreated());

        userMapper.insert(user);

        //将用户名直接返回
        return user.getUsername();
    }
}
