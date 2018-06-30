package com.jt.web.service;

import com.jt.web.pojo.Item;
import io.swagger.models.auth.In;

import java.util.List;

public interface SearchService {
    /**
     * 关键字搜索
     * @param keyword
     * @return
     */
    List<Item> findItemListByKeyword(String keyword, Integer page, Integer rows);
}
