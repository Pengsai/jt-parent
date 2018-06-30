package com.jt.search.controller;

import com.jt.search.pojo.Item;
import com.jt.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @ClassName SerachController
 * @Description TODO
 * @Author PS
 * @Date 2018/6/29 16:56
 **/
@Controller
public class SerachController {
    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "/search",method = RequestMethod.POST)
    @ResponseBody
    public List<Item> findItemListByKeyWord(String keyword, Integer page, Integer rows){

        List<Item> itemList = searchService.findItemListBykeyWord(keyword, page, rows);

        return itemList;
    }

}
