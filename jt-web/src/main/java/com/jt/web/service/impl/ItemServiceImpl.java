package com.jt.web.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.service.HttpClientService;
import com.jt.web.pojo.Item;
import com.jt.web.service.ItemService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName ItemServiceImpl
 * @Description TODO
 * @Author PS
 * @Date 2018/6/14 20:55
 **/
@Service
public class ItemServiceImpl implements ItemService{

    @Autowired
    private HttpClientService httpClientService;

    //将java数据和JSON串之间进行转化
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger looger = Logger.getLogger(ItemServiceImpl.class);

    @Override
    public Item findItemById(Long itemId) {

        String url = "http://manage.jt.com/web/item/"+itemId;

        try {
            String itemJson = httpClientService.doGet(url);

            Item item = objectMapper.readValue(itemJson, Item.class);

            return item;


        } catch (Exception e) {
            e.printStackTrace();
            looger.error(e.getMessage());
        }


        return null;
    }
}
