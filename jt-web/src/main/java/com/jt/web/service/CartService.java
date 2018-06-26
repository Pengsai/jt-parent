package com.jt.web.service;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;

import java.util.List;

public interface CartService {
    /**
     * 去购物车
     * @param userId
     * @return
     */
    List<Cart> findCartListByUserList(Long userId);

    /**
     * 新增购物车
     * @param cart
     */
    SysResult insertCart(Cart cart);


    /**
     * 购物车修改商品数量
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
    void deleteCart(Long userId, Long itemId);
}
