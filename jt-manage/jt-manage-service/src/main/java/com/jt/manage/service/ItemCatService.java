package com.jt.manage.service;

import com.jt.common.vo.ItemCatResult;
import com.jt.manage.pojo.ItemCat;

import java.util.List;

public interface ItemCatService {

    /**
     * 根据id查询下级列表
     * @param id
     * @return
     */
    List<ItemCat> findItemCatList(Long id);

    /**
     * 查找所有的三级分类菜单
     * @return
     */
    ItemCatResult findItemCatAll();
}
