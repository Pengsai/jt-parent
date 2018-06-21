package com.jt.manage.controller.web;

import com.jt.manage.pojo.Item;
import com.jt.manage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @ClassName WebItemController
 * @Description TODO
 * @Author PS
 * @Date 2018/6/21 11:29
 **/
@Controller
@RequestMapping("/web")
public class WebItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/item/{itemId}")
    @ResponseBody
    public Item findItemById(@PathVariable Long itemId) {

        return itemService.findItemById(itemId);
    }
}
