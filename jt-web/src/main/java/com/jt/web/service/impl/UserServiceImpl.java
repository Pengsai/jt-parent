package com.jt.web.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.jt.common.service.HttpClientService;
import com.jt.common.service.SysProperties;
import com.jt.common.util.Beans;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.User;
import com.jt.web.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName UserServiceImpl
 * @Description TODO
 * @Author PS
 * @Date 2018/6/21 15:17
 **/
@Service
public class UserServiceImpl implements UserService {

    private static final Logger looger = Logger.getLogger(ItemServiceImpl.class);

    @Autowired
    private HttpClientService httpClientService;

    private static final ObjectMapper objectMapper = new ObjectMapper();


    @Override
    public SysResult doRegister(User user) {

        Map<String, Object> userMap = Maps.newHashMap();

        try {
            Beans.transformBeanToMap(user, userMap);

            String registerUrl = SysProperties.interUrl.registerUrl;

            String sysResultJSON = httpClientService.doPost(registerUrl, userMap);

            JsonNode jsonNode = objectMapper.readTree(sysResultJSON);
            String data = jsonNode.get("data").asText();
            looger.info("注册返回数据="+data);

            return objectMapper.readValue(sysResultJSON, SysResult.class);
        } catch (Exception e) {
            looger.error(e.getMessage());
            e.printStackTrace();

            return SysResult.build(201, "注册失败");
        }

    }

    @Override
    public SysResult doLogin(String username, String password) {

        String loginUrl = SysProperties.interUrl.loginUrl;

        HashMap<String, Object> userMap = Maps.newHashMap();

        userMap.put("u", username);
        userMap.put("p", password);

        try {
            String sysresultJSON = httpClientService.doPost(loginUrl, userMap);

            return objectMapper.readValue(sysresultJSON, SysResult.class);
        } catch (Exception e) {
            e.printStackTrace();
            looger.error(e.getMessage());
            return SysResult.build(201, "用户登陆失败");
        }


    }












}
