package com.jt.web.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.jt.common.service.HttpClientService;
import com.jt.common.service.SysProperties;
import com.jt.common.util.Beans;
import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;
import com.jt.web.service.CartService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName CartServiceImpl
 * @Description TODO
 * @Author PS
 * @Date 2018/6/25 14:39
 **/
@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private HttpClientService httpClientService;

    private static final Logger logger = Logger.getLogger(CartServiceImpl.class);

    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<Cart> findCartListByUserList(Long userId) {

        String cartUrl = SysProperties.interUrl.toCartUrl + userId;


        try {
            String sysResultJSON = httpClientService.doGet(cartUrl);
            //将JSON串转化为节点信息
            JsonNode jsonNode = objectMapper.readTree(sysResultJSON);

            //[{},{},{},{}]  获取JSON串中获取list集合数据
            String data = jsonNode.get("data").asText();

            //将JOSN数据转化为Carts对象
            Cart[] carts = objectMapper.readValue(data, Cart[].class);

            List<Cart> cartList = new ArrayList<>();
            //将对象赋值到List集合中
            for (Cart cart : carts) {
                cartList.add(cart);
            }
            return cartList;

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return null;
        }
    }

    @Override
    public SysResult insertCart(Cart cart) {

        String saveCartUrl = SysProperties.interUrl.saveCartUrl;

        Map<String,Object> cartMap = Maps.newHashMap();

        try {
            Beans.transformBeanToMap(cart, cartMap);

            String sysResultJSON = httpClientService.doPost(saveCartUrl, cartMap);

            SysResult sysResult = objectMapper.readValue(sysResultJSON, SysResult.class);

            return sysResult;

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return SysResult.build(201, "商品新增失败");
        }

    }

    @Override
    public SysResult updateCartNum(Long userId, Long itemId, Integer num) {

        String saveCartNumUrl = SysProperties.interUrl.saveCartNumUrl+userId+"/"+itemId+"/"+num;
        try {
            String sysResultJSON = httpClientService.doGet(saveCartNumUrl);

            SysResult sysResult = objectMapper.readValue(sysResultJSON, SysResult.class);

            return sysResult;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return SysResult.build(201, "商品数量修改失败");
        }
    }

    @Override
    public void deleteCart(Long userId, Long itemId) {

        String deleteCartUrl = SysProperties.interUrl.deleteCartUrl+userId+"/"+itemId;

        try {
            String sysResultJSON = httpClientService.doGet(deleteCartUrl);

            objectMapper.readValue(sysResultJSON, SysResult.class);

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }
    }
}
