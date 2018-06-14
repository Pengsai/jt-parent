package com.jt.manage.controller;

import com.jt.manage.pojo.ItemCat;
import com.jt.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import redis.clients.jedis.JedisCluster;

import java.util.List;

/**
 * @ClassName ItemCatController
 * @Description 商品分类
 * @Author PS
 * @Date 2018/6/5 20:36
 **/
@Controller
@RequestMapping("/item/cat")
public class ItemCatController {

    @Autowired
    private ItemCatService itemCatService;



    @RequestMapping(value = "/list")
    @ResponseBody
    public List<ItemCat> findItemCatList(@RequestParam(defaultValue = "0")Long id ){

        return itemCatService.findItemCatList(id);
    }
}
