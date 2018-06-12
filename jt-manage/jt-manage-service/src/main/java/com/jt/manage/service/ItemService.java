package com.jt.manage.service;

import com.jt.common.vo.EasyUIResult;
import com.jt.manage.pojo.Item;

public interface ItemService {

    EasyUIResult findItemList(int page, int rows);

    /**
     * 根据分类id 查询所属分类
     * @param cid
     * @return
     */
    String findItemCatName(Long cid);

    /**
     * 商品新增
     * @param item
     */
    void saveItem(Item item);

    /**
     * 商品修改
     * @param item
     */
    void updateItem(Item item);
}
