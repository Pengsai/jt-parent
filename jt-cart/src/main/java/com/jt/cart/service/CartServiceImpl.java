package com.jt.cart.service;

import com.jt.cart.mapper.CartMapper;
import com.jt.cart.pojo.Cart;
import com.jt.common.mapper.SysMapper;
import com.jt.common.service.BaseService;
import com.jt.common.vo.SysResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @ClassName CartServiceImpl
 * @Description TODO
 * @Author PS
 * @Date 2018/6/25 14:32
 **/
@Service
public class CartServiceImpl extends BaseService<Cart> implements CartService {
    @Autowired
    private CartMapper cartMapper;

    private static final Logger logger = Logger.getLogger(CartServiceImpl.class);

    @Override
    public List<Cart> findCartListByUserList(Long userId) {

        Cart cart = new Cart();
        cart.setUserId(userId);
        return cartMapper.select(cart);
    }

    @Override
    public void saveCart(Cart cart) {
        /**
         * 如果购物车有该数据，则增加商品数目
         * 如果没有，则新增一条记录
         */

        Cart cartDB = new Cart();
        cartDB.setItemId(cart.getItemId());
        cartDB.setUserId(cart.getUserId());
        Cart findCart = super.queryByWhere(cartDB);
        if (findCart != null) {
            int count = cart.getNum() + findCart.getNum();
            findCart.setNum(count);
            findCart.setUpdated(new Date());
            cartMapper.updateByPrimaryKeySelective(findCart);
        } else {
            cart.setCreated(new Date());
            cart.setUpdated(cart.getCreated());
            cartMapper.insert(cart);
        }


    }

    @Override
    public SysResult updateCartNum(Long userId, Long itemId, Integer num) {

        Cart cart = new Cart();
        cart.setUserId(userId);
        cart.setItemId(itemId);
        cart.setUpdated(new Date());
        cart.setNum(num);
        try {
            cartMapper.updateCartNum(cart);

            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return SysResult.build(201, "修改商品数量失败");
        }

    }

    @Override
    public SysResult deleteCart(Long userId, Long itemId) {

        Cart cart = new Cart();
        cart.setItemId(itemId);
        cart.setUserId(userId);
        try {
            cartMapper.delete(cart);
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return SysResult.build(201, "购物车删除失败");
        }

    }
}
