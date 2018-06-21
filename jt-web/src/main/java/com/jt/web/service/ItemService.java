package com.jt.web.service;

import com.jt.web.pojo.Item;

/**
 * @ClassName ItemService
 * @Description TODO
 * @Author PS
 * @Date 2018/6/14 20:54
 **/
public interface ItemService {

    /**
     * 根据商品id查找商品
     * @param itemId
     * @return
     */
    Item findItemById(Long itemId);
}
