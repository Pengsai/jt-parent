package com.jt.sso.mapper;

import com.jt.common.mapper.SysMapper;
import com.jt.sso.pojo.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;


@Repository
public interface UserMapper extends SysMapper<User>{

    int findCheckUser(@Param("cloumn") String cloumn, @Param("param") String param);
}
