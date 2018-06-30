package com.jt.web.controller;

import com.jt.web.pojo.Item;
import com.jt.web.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.util.List;

/**
 * @ClassName SearchController
 * @Description TODO
 * @Author PS
 * @Date 2018/6/27 17:45
 **/

@Controller
public class SearchController {

    @Autowired
    private SearchService searchService;

    @RequestMapping(value = "search",method= RequestMethod.GET)
    public String findItemListByKeyword(@RequestParam("q") String keyword,
                                        @RequestParam(defaultValue = "1") Integer page, Model model) {

        try {
            keyword = new String(keyword.getBytes("iso-8859-1"), "utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        int rows = 20;
        List<Item> items = searchService.findItemListByKeyword(keyword,page,rows);

        model.addAttribute("itemList", items);
        model.addAttribute("query", keyword);

        return "search";
    }


}
