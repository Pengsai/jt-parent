package com.jt.search.service;

import com.jt.search.pojo.Item;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @ClassName SearchServiceImpl
 * @Description TODO
 * @Author PS
 * @Date 2018/6/29 16:57
 **/
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    private HttpSolrServer httpSolrServer;

    @Override
    public List<Item> findItemListBykeyWord(String keyword, Integer page, Integer rows) {

        //定义solr查询的工具类
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setStart((page-1)*rows);  //从第0条数据开始
        solrQuery.setRows(rows);  //每页展现20条记录
        solrQuery.setQuery(keyword); //根据关键字查询solr的数据库

        try {
            //表示通过linux中的程序  通过索引文件获取list集合信息
            QueryResponse queryResponse = httpSolrServer.query(solrQuery);
            List<Item> itemList = queryResponse.getBeans(Item.class);
            return itemList;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
