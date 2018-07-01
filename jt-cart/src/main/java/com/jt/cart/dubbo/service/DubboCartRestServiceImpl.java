package com.jt.cart.dubbo.service;

import java.util.List;

import com.jt.cart.service.CartService;
import com.jt.dubbo.service.DubboCartRestService;
import org.springframework.beans.factory.annotation.Autowired;

import com.jt.dubbo.pojo.Cart;

public class DubboCartRestServiceImpl implements DubboCartRestService {
    @Autowired
    private CartService cartService;

    //我的购物车
    public List<Cart> myCartList(Long userId) {
        List<Cart> cartList = cartService.findCartListByUserList(userId);
        return cartList;
    }

}
