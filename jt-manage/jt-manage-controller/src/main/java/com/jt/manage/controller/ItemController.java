package com.jt.manage.controller;

import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "/query")
    @ResponseBody
    public EasyUIResult findItemList(int page, int rows){
        return itemService.findItemList(page,rows);
    }

    @RequestMapping(value = "/query/name", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String findItemCatList(Long cid, HttpServletResponse response){

        return itemService.findItemCatName(cid);
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public SysResult saveItem(Item item){

        try{
            itemService.saveItem(item);
            return SysResult.build(200, "商品新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(201, "商品新增失败");
        }
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public SysResult updateItem(Item item){

        try{
            itemService.updateItem(item);
            return SysResult.build(200, "商品修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            return SysResult.build(201, "商品修改失败");
        }
    }


}
