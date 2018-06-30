package com.jt.web.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.Maps;
import com.jt.common.service.HttpClientService;
import com.jt.common.service.SysProperties;
import com.jt.web.pojo.Item;
import com.jt.web.service.SearchService;
import io.swagger.models.auth.In;
import net.sf.jsqlparser.expression.operators.relational.ItemsList;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SearchServiceImpl
 * @Description TODO
 * @Author PS
 * @Date 2018/6/27 17:46
 **/
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private HttpClientService httpClientService;

    //将java数据和JSON串之间进行转化
    private static final ObjectMapper objectMapper = new ObjectMapper();

    private static final Logger logger = Logger.getLogger(ItemServiceImpl.class);

    @Override
    public List<Item> findItemListByKeyword(String keyword, Integer page, Integer rows) {

        List<Item> itemList = new ArrayList<>();

        String searchUrl = SysProperties.interUrl.searchUrl;
        Map<String, Object> map = Maps.newHashMap();
        map.put("keyword", keyword);
        map.put("page", page+"");
        map.put("rows", rows+"");

        try {
            String resultJson = httpClientService.doPost(searchUrl, map);

            Item[] items = objectMapper.readValue(resultJson, Item[].class);

            for (Item item : items) {
                itemList.add(item);
            }
            return itemList;

        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
        }

        return null;
    }
}
