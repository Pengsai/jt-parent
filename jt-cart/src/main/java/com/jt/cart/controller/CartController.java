package com.jt.cart.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.cart.pojo.Cart;
import com.jt.cart.service.CartService;
import com.jt.common.vo.SysResult;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName CartController
 * @Description 后台购物车
 * @Author PS
 * @Date 2018/6/25 14:28
 **/
@Controller
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    private static final ObjectMapper objectMapper = new ObjectMapper();
    private static final Logger logger = Logger.getLogger(CartController.class);


    @RequestMapping(value = "/query/{userId}", method = RequestMethod.GET)
    @ResponseBody
    public SysResult toCart(@PathVariable Long userId) {

        try {

            List<Cart> cartList = cartService.findCartListByUserList(userId);

            String cartJSON = objectMapper.writeValueAsString(cartList);
            return SysResult.oK(cartJSON);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return SysResult.build(201, "查询购物车失败");
        }

    }

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    @ResponseBody
    public SysResult saveCart(Cart cart) {

        try {
            cartService.saveCart(cart);
            return SysResult.oK();
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return SysResult.build(201, "商品新增失败");
        }

    }

    @RequestMapping(value = "/update/num/{userId}/{itemId}/{num}", method = RequestMethod.GET)
    @ResponseBody
    public SysResult updateCartNum(@PathVariable Long userId,
                                   @PathVariable Long itemId,
                                   @PathVariable Integer num) {

        try {
            SysResult sysResult = cartService.updateCartNum(userId, itemId, num);

            return sysResult;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return SysResult.build(201, "修改商品数量失败");
        }

    }

    @RequestMapping(value = "/delete/{userId}/{itemId}", method = RequestMethod.GET)
    @ResponseBody
    public SysResult deleteCart(@PathVariable Long userId,
                                   @PathVariable Long itemId) {

        try {
            SysResult sysResult = cartService.deleteCart(userId, itemId);
            return sysResult;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return SysResult.build(201, "删除购物车失败");
        }

    }

}
