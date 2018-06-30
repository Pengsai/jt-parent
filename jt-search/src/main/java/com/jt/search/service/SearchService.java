package com.jt.search.service;

import com.jt.search.pojo.Item;

import java.util.List;

public interface SearchService {

    List<Item> findItemListBykeyWord(String keyword, Integer page, Integer rows);
}
