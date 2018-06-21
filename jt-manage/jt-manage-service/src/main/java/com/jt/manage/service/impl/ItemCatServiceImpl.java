package com.jt.manage.service.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jt.common.util.RedisUtils;
import com.jt.common.vo.ItemCatData;
import com.jt.common.vo.ItemCatResult;
import com.jt.manage.mapper.ItemCatMapper;
import com.jt.manage.pojo.ItemCat;
import com.jt.manage.service.ItemCatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @ClassName ItemCatServiceImpl
 * @Description TODO
 * @Author PS
 * @Date 2018/6/5 20:43
 **/
@Service
public class ItemCatServiceImpl implements ItemCatService {

    @Autowired
    private ItemCatMapper itemCatMapper;

    @Qualifier("redisPool")
    @Autowired
    private RedisUtils redisUtils;

    //将java数据和JSON串之间进行转化
    private static final ObjectMapper objectMapper = new ObjectMapper();

    @Override
    public List<ItemCat> findItemCatList(Long parentId) {

        /**
         * 先读缓存，缓存没有再读数据库
         */

        String itemCatKey = "ITEM_CAT_" + parentId;

        String itemCatsJson = redisUtils.get(itemCatKey);

        List<ItemCat> itemCatList = new ArrayList<>();


        try{
            if (StringUtils.isEmpty(itemCatsJson)) {
                ItemCat itemCat = new ItemCat();
                itemCat.setParentId(parentId);
                itemCatList = itemCatMapper.select(itemCat);

                String JSONResult = objectMapper.writeValueAsString(itemCatList);

                redisUtils.set(itemCatKey, JSONResult, 20*60);

                return itemCatList;

            } else {

                ItemCat[] itemCats = objectMapper.readValue(itemCatsJson, ItemCat[].class);

                for (ItemCat itemCat : itemCats) {
                    itemCatList.add(itemCat);
                }
                return itemCatList;
            }
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return itemCatList;
    }

    @Override
    public ItemCatResult findItemCatAll() {
        //表示查询商品的全部信息 上架的商品
        ItemCat itemCatAll = new ItemCat();
        itemCatAll.setStatus(1);
        List<ItemCat> itemCatList = itemCatMapper.select(itemCatAll);

        //定义一个数据中转的集合   long数据表示上级的Id  List<ItemCat> 当前父级下的所有子级菜单
        Map<Long, List<ItemCat>> mapItem = new HashMap<>();

        for (ItemCat itemCat : itemCatList) {
            if (mapItem.containsKey(itemCat.getParentId())) {
                // 上级已经放入了集合中
                mapItem.get(itemCat.getParentId()).add(itemCat);
            } else {
                List<ItemCat> itemCatMapList = new ArrayList<>();
                itemCatMapList.add(itemCat);
                mapItem.put(itemCat.getParentId(), itemCatMapList);
            }
        }

        //定义一级菜单list集合
        List<ItemCatData> itemCatList1 = new ArrayList<>();

        //循环遍历一级菜单
        for (ItemCat itemCat : mapItem.get(0L)) {
            ItemCatData itemCatData1 = new ItemCatData();
            itemCatData1.setUrl("/products/"+itemCat.getId()+".html");
            itemCatData1.setName("<a href='"+itemCatData1.getUrl()+"'>"+itemCat.getName()+"</a>");

            //准备当前菜单下的二级菜单
            List<ItemCatData> itemCatList2 = new ArrayList<ItemCatData>();
            for (ItemCat itemCat2 :mapItem.get(itemCat.getId())) {
                ItemCatData itemCatData2 = new ItemCatData();
                itemCatData2.setUrl("/products/"+itemCat2.getId());
                itemCatData2.setName(itemCat2.getName());

                //准备3级分类菜单
                List<String> itemCatList3 = new ArrayList<String>();
                for (ItemCat itemCat3 :mapItem.get(itemCat2.getId())) {
                    itemCatList3.add("/products/"+itemCat3.getId()+"|"+itemCat3.getName());
                }

                itemCatData2.setItems(itemCatList3);
                itemCatList2.add(itemCatData2);
            }

            //将2级菜单list集合存入一级菜单对象
            itemCatData1.setItems(itemCatList2);

            if(itemCatList1.size()>13)
                break;

            //将一级菜单的对象保存入list集合中
            itemCatList1.add(itemCatData1);
        }

        ItemCatResult itemCatResult = new ItemCatResult();
        itemCatResult.setItemCats(itemCatList1);

        return itemCatResult;
    }

    /*@Override
    public Object findItemCatAll() {
        ItemCat record = new ItemCat();
        record.setParentId(0L);
        record.setStatus(1);
        List<BaseTree> list = new ArrayList<BaseTree>();
        List<BaseTree> treelist = new ArrayList<BaseTree>();
        List<ItemCat> itemCats = itemCatMapper.select(record);
        for (ItemCat itemCat : itemCats) {
            // 第一级
            BaseTree baseTree = new BaseTree();
            baseTree.setId(String.valueOf(itemCat.getId()));
            baseTree.setValue1("/products/"+itemCat.getId()+".html");
            baseTree.setValue2("<a href='url'>"+itemCat.getName());
            list.add(baseTree);
            // 第二级
            ItemCat temp = new ItemCat();
            temp.setParentId(itemCat.getId());
            temp.setStatus(1);
            List<ItemCat> itemCats2 = itemCatMapper.select(temp);

            if (itemCats2!=null) {
                for (ItemCat itemCat2 : itemCats2) {
                    BaseTree child = new BaseTree();
                    child.setId(String.valueOf(itemCat2.getId()));
                    child.setValue1("/products/"+itemCat.getId()+".html");
                    child.setValue2(itemCat.getName());
                    child.setParentId(baseTree.getId());
                    List<String> items2 = new ArrayList<>();

                    // 第三级
                    ItemCat temp2 = new ItemCat();
                    temp2.setParentId(itemCat2.getId());
                    temp2.setStatus(1);
                    List<ItemCat> itemCats3 = itemCatMapper.select(temp2);
                    List<String> strings = new ArrayList<>();
                    if (itemCats3!=null) {
                        for (ItemCat itemCat3 : itemCats3) {
                            strings.add("/products/"+itemCat3.getId()+"|"+itemCat3.getName());
                        }
                    }

                    child.setValue3(strings);

                    list.add(child);

                }
            }

        }

        treelist = formatTree(list, true);

        String json = JSONArray.toJSONString(treelist);
        return json;

    }*/


}
