package com.jt.sso.mapper;

import com.jt.common.mapper.SysMapper;
import com.jt.sso.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper extends SysMapper<User>{

    int findCheckUser(@Param("cloumn") String cloumn, @Param("param") String param);

    /**
     * 通过用户名和密码查询用户
     * @param username
     * @param md5Password
     * @return
     */
    User findUserByU_P(@Param("username") String username, @Param("password") String md5Password);
}
