package com.jt.manage.service;

import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.pojo.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Override
    public EasyUIResult findItemList(int page, int rows) {

        // 起始页数
        int begin = (page - 1) * rows;
        List<Item> itemList = itemMapper.findItemList(begin, rows);
        int count = itemMapper.selectCount(null);
        return new EasyUIResult(count, itemList);
    }

    @Override
    public String findItemCatName(Long cid) {
        
        return itemMapper.findItemName(cid);
    }

    @Override
    public void saveItem(Item item) {

        item.setCreated(new Date());
        item.setUpdated(item.getCreated());
        // 表示正常
        item.setStatus(1);

        itemMapper.insert(item);

    }

    @Override
    public void updateItem(Item item) {
        item.setUpdated(new Date());

        itemMapper.updateByPrimaryKeySelective(item);
    }
}
