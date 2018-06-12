package com.jt.manage.service;

import com.jt.manage.pojo.ItemCat;

import java.util.List;

public interface ItemCatService {

    /**
     * 根据id查询下级列表
     * @param id
     * @return
     */
    List<ItemCat> findItemCatList(Long id);

}
