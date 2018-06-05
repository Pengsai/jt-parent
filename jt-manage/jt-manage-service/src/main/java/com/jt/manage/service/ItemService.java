package com.jt.manage.service;

import com.jt.common.vo.EasyUIResult;

public interface ItemService {

    EasyUIResult findItemList(int page, int rows);

    /**
     * 根据分类id 查询所属分类
     * @param cid
     * @return
     */
    String findItemCatName(Long cid);
}
