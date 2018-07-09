package com.jt.cart.dubbo.service;

import com.jt.cart.service.CartService;
import com.jt.common.vo.SysResult;
import com.jt.dubbo.pojo.Cart;
import com.jt.dubbo.service.DubboCartRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DubboCartRestServiceImpl implements DubboCartRestService {
    @Autowired
    private CartService cartService;

    //我的购物车
    public List<Cart> myCartList(Long userId) {
        List<Cart> cartList = cartService.findCartListByUserList(userId);
        return cartList;
    }

    /*@Override
    public SysResult saveCart(Long userId, Long itemId, String itemTitle, String itemImage, Long itemPrice, Integer num) {
        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setItemId(itemId);
        cart.setItemTitle(itemTitle);
        cart.setItemImage(itemImage);
        cart.setItemPrice(itemPrice);
        cart.setNum(num);

        cart.setCreated(new Date());
        cart.setUpdated(cart.getCreated());

        return cartService.saveCart(cart);


    }*/

    @Override
    public SysResult saveCart(Cart cart) {

        cart.setCreated(new Date());
        cart.setUpdated(cart.getCreated());

        return cartService.saveCart(cart);


    }

    @Override
    public SysResult updateCartNum(Long userId, Long itemId, Integer num) {
        return cartService.updateCartNum(userId, itemId, num);
    }

    @Override
    public SysResult deleteCart(Long userId, Long itemId) {
        return cartService.deleteCart(userId, itemId);
    }


}
