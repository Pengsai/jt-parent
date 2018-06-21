package com.jt.manage.controller.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.vo.ItemCatResult;
import com.jt.manage.pojo.ItemCat;
import com.jt.manage.service.ItemCatService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.converter.json.MappingJacksonValue;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @ClassName WebItemCatController
 * @Description TODO
 * @Author PS
 * @Date 2018/6/15 15:28
 **/

@Controller
public class WebItemCatController {

    @Autowired
    private ItemCatService itemCatService;

    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger looger = Logger.getLogger(WebItemCatController.class);


    @RequestMapping(value = "/web/itemcat/all")
    @ResponseBody
    public Object findItemCatAll(String callback){

        ItemCatResult itemCatAll = itemCatService.findItemCatAll();

        MappingJacksonValue jacksonValue = new MappingJacksonValue(itemCatAll);

        jacksonValue.setJsonpFunction(callback);

        return jacksonValue;
    }
}
