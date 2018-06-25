package com.jt.web.controller;

import com.jt.web.pojo.Item;
import com.jt.web.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @ClassName ItemController
 * @Description 前台商品
 * @Author PS
 * @Date 2018/6/14 20:51
 **/
@Controller
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping("/items/{itemId}")
    public String findItemCatAll(@PathVariable Long itemId, Model model) {

        Item item = itemService.findItemById(itemId);

        model.addAttribute("item", item);

        return "item";

    }
}
