package com.jt.manage.controller;

import com.jt.common.vo.EasyUIResult;
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

}
