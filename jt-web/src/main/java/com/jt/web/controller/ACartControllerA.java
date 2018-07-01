package com.jt.web.controller;

import com.jt.common.vo.SysResult;
import com.jt.web.pojo.Cart;
import com.jt.web.service.CartService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName CartController
 * @Description 前台购物车
 * @Author PS
 * @Date 2018/6/25 14:37
 **/
@Controller
@RequestMapping("/cartA")
public class ACartControllerA {

    @Autowired
    private CartService cartService;

    private static final Logger logger = Logger.getLogger(ACartControllerA.class);

    @RequestMapping(value = "/show", method = RequestMethod.GET)
    public String toCart(Model model) {

        Long userId = 3L;

        List<Cart> cartList = cartService.findCartListByUserList(userId);
        model.addAttribute("cartList", cartList);

        //表示转向购物车页面
        return "cart";
    }

    @RequestMapping(value = "/update/num/{itemId}/{num}", method = RequestMethod.POST)
    @ResponseBody
    public SysResult updateCartNum(@PathVariable Long itemId, @PathVariable Integer num) {

        try {
            Long userId = 3L;
            SysResult sysResult = cartService.updateCartNum(userId, itemId, num);
            return sysResult;
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return SysResult.build(201, "修改商品数量失败");
        }

    }

    @RequestMapping(value = "/delete/{itemId}", method = RequestMethod.GET)
    public String  deleteCart(@PathVariable Long itemId) {

            Long userId = 3L;
            cartService.deleteCart(userId, itemId);
            return "redirect:/cart/show.html";

    }

    @RequestMapping(value = "/add/{itemId}", method = RequestMethod.POST)
    public String insertCart(@PathVariable Long itemId, Cart cart) {

        Long userId = 3L;

        cart.setUserId(userId);
        cart.setItemId(itemId);

        cartService.insertCart(cart);

        return "redirect:/cart/show.html";
    }
}
