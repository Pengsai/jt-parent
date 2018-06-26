package com.jt.cart.service;

import com.jt.cart.pojo.Cart;
import com.jt.common.vo.SysResult;

import java.util.List;

public interface CartService {

    /**
     * 通过userId查找购物车
     * @param userId
     * @return
     */
    List<Cart> findCartListByUserList(Long userId);

    /**
     * 购物车新增商品
     * @param cart
     */
    void saveCart(Cart cart);

    /**
     * 修改商品数量
     * @param userId
     * @param itemId
     * @param num
     * @return
     */
    SysResult updateCartNum(Long userId, Long itemId, Integer num);

    /**
     * 删除购物车
     * @param userId
     * @param itemId
     * @return
     */
    SysResult deleteCart(Long userId, Long itemId);
}
