package com.jt.manage.service;

import com.jt.common.vo.EasyUIResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;

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
    void saveItem(Item item, String desc);

    /**
     * 商品修改
     * @param item
     */
    void updateItem(Item item, String desc);

    /**
     * 商品删除
     * @param ids
     */
    void deleteItem(Long[] ids);

    /**
     * 修改商品状态
     * @param ids
     * @param status
     */
    void updateStatus(Long[] ids, int status);

    /**
     * 查询商品详情
     * @param itemId
     * @return
     */
    ItemDesc findItemDescById(Long itemId);
}
