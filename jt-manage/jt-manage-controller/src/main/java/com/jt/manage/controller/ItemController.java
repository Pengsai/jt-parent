package com.jt.manage.controller;

import com.jt.common.service.SysProperties;
import com.jt.common.vo.EasyUIResult;
import com.jt.common.vo.SysResult;
import com.jt.manage.pojo.Item;
import com.jt.manage.pojo.ItemDesc;
import com.jt.manage.service.ItemService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.Arrays;


@Controller
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;




    //获取日志对象
    private static final Logger logger = Logger.getLogger(ItemController.class);

    @RequestMapping(value = "/query")
    @ResponseBody
    public EasyUIResult findItemList(int page, int rows){
        return itemService.findItemList(page,rows);
    }

    @RequestMapping(value = "/query/name", produces = "text/html;charset=utf-8")
    @ResponseBody
    public String findItemCatList(Long cid, HttpServletResponse response){

        return itemService.findItemCatName(cid);
    }

    @RequestMapping(value = "/save")
    @ResponseBody
    public SysResult saveItem(Item item, String desc){

        try{
            itemService.saveItem(item, desc);
            logger.info("{商品新增成功}");
            return SysResult.build(200, "商品新增成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("{新增商品失败}");
            return SysResult.build(201, "商品新增失败");
        }
    }

    @RequestMapping(value = "/update")
    @ResponseBody
    public SysResult updateItem(Item item, String desc){

        try{
            itemService.updateItem(item, desc);
            logger.info("{商品修改成功}");
            return SysResult.build(200, "商品修改成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("{修改商品失败}");
            return SysResult.build(201, "商品修改失败");
        }
    }

    @RequestMapping(value = "/delete")
    @ResponseBody
    public SysResult deleteItem(Long[] ids){

        try{
            itemService.deleteItem(ids);
            logger.info("{商品删除成功"+ Arrays.toString(ids)+"}");
            return SysResult.build(200, "商品删除成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("{"+e.getMessage()+"}");
            return SysResult.build(201, "商品删除失败");
        }
    }
    @RequestMapping(value = "/instock",method = RequestMethod.GET)
    @ResponseBody
    public SysResult instockItem(Long[] ids){

        try{

            itemService.updateStatus(ids,SysProperties.itemStatus.instock);
            logger.info("{商品下架成功}");
            return SysResult.build(200, "商品下架成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return SysResult.build(201, "商品下架失败");
        }
    }



    @RequestMapping(value = "/reshelf",method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "商品上架", httpMethod = "get", notes = "商品上架")
    public SysResult reshelfItem(Long[] ids){

        try{
            itemService.updateStatus(ids, SysProperties.itemStatus.reshelf);
            logger.info("{商品上架成功}");
            return SysResult.build(200, "商品上架成功");
        } catch (Exception e) {
            e.printStackTrace();
            logger.error(e.getMessage());
            return SysResult.build(201, "商品上架失败");
        }
    }

    //根据itemId获取商品描述信息
    @RequestMapping(value = "/query/item/desc/{itemId}", method = RequestMethod.GET)
    @ResponseBody
    @ApiOperation(value = "商品", httpMethod = "get", notes = "根据itemId获取商品描述信息")
    public SysResult findItemDescById(@ApiParam(required = true, name = "itemId", value = "商品Id")@PathVariable Long itemId){

        try {
            ItemDesc itemDesc = itemService.findItemDescById(itemId);
            logger.info("{商品描述信息查询成功"+itemId+"}");
            return SysResult.build(200, "商品描述查询成功", itemDesc);
        } catch (Exception e) {
            e.printStackTrace();
            logger.error("{商品描述信息查询失败}");
            return SysResult.build(201, "商品描述信息查询失败");
        }

    }



}
