package com.jt.manage.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.util.RedisUtils;
import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.pojo.ItemCat;
import com.jt.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ItemCatServiceImpl
 * @Description TODO
 * @Author PS
 * @Date 2018/6/5 20:43
 **/
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Autowired
    private RedisUtils redisUtils;

    //将java数据和JSON串之间进行转化
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<ItemCat> findItemCatList(Long parentId) {

        /**
         * 先读缓存，缓存没有再读数据库
         */

        String itemCatKey = "ITEM_CAT_" + parentId;

        String itemCatsJson = redisUtils.get(itemCatKey);

        List<ItemCat> itemCatList = new ArrayList<>();


        try{
            if (StringUtils.isEmpty(itemCatsJson)) {
                ItemCat itemCat = new ItemCat();
                itemCat.setParentId(parentId);
                itemCatList = itemCatMapper.select(itemCat);

                String JSONResult = objectMapper.writeValueAsString(itemCatList);

                redisUtils.set(itemCatKey, JSONResult, 20*60);

                return itemCatList;

            } else {

                ItemCat[] itemCats = objectMapper.readValue(itemCatsJson, ItemCat[].class);

                for (ItemCat itemCat : itemCats) {
                    itemCatList.add(itemCat);
                }
                return itemCatList;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return itemCatList;
    }


}
