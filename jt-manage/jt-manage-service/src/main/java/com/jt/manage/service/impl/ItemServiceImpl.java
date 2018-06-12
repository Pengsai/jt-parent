package com.jt.manage.service.impl;

import com.jt.common.service.SysProperties;
import com.jt.common.vo.EasyUIResult;
import com.jt.manage.mapper.ItemDescMapper;
import com.jt.manage.mapper.ItemMapper;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemMapper itemMapper;

    @Autowired
    private ItemDescMapper itemDescMapper;

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
    public void saveItem(Item item, String desc) {

        item.setCreated(new Date());
        item.setUpdated(item.getCreated());
        // 表示正常
        item.setStatus(SysProperties.itemStatus.reshelf);
        itemMapper.insert(item);
        // 插入商品详情
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(item.getId());
        itemDesc.setItemDesc(desc);
        itemDesc.setCreated(item.getCreated());
        itemDesc.setUpdated(item.getUpdated());
        itemDescMapper.insert(itemDesc);

    }

    @Override
    public void updateItem(Item item) {
        item.setUpdated(new Date());

        itemMapper.updateByPrimaryKeySelective(item);
    }

    @Override
    public void deleteItem(Long[] ids) {
        itemMapper.deleteByIDS(ids);
    }

    @Override
    public void updateStatus(Long[] ids, int status) {
        itemMapper.updateStatus(ids, status);
    }

    @Override
    public ItemDesc findItemDescById(Long itemId) {
        ItemDesc itemDesc = new ItemDesc();
        itemDesc.setItemId(itemId);
        return itemDescMapper.selectByPrimaryKey(itemDesc);
    }


}
