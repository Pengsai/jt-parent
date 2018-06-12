package com.jt.manage.service.impl;

import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.pojo.ItemCat;
import com.jt.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    @Override
    public List<ItemCat> findItemCatList(Long id) {

        ItemCat itemCat = new ItemCat();
        itemCat.setParentId(id);

        return itemCatMapper.select(itemCat);
    }
}
