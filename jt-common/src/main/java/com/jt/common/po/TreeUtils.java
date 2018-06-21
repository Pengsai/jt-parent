package com.jt.common.po;

/**
 * @ClassName test
 * @Description TODO
 * @Author PS
 * @Date 2018/6/13 14:50
 **/

import com.alibaba.fastjson.JSONArray;

import java.util.ArrayList;
import java.util.List;

/**
 * 将记录list转化为树形list
 * 基于BaseTreeGid类的转换
 *
 */
public class TreeUtils {

    /**
     * 格式化list为树形list
     * @param list
     * @param flag true 表示全部展开，其他 表示不展开
     * @return
     */
    public static <T extends BaseTree> List<T> formatTree(List<T> list, Boolean flag) {

        List<T> nodeList = new ArrayList<T>();
        for(T node1 : list){
            boolean mark = false;
            for(T node2 : list){
                if(node1.getParentId()!=null && node1.getParentId().equals(node2.getId())){
                    node2.setLeaf(false);
                    mark = true;
                    if(node2.getChildren() == null) {
                        node2.setChildren(new ArrayList<BaseTree>());
                    }
                    node2.getChildren().add(node1);
                    if (flag) {
                        //默认已经全部展开
                    } else{
                        node2.setExpanded(false);
                    }
                    break;
                }
            }
            if(!mark){
                nodeList.add(node1);
                if (flag) {
                    //默认已经全部展开
                } else{
                    node1.setExpanded(false);
                }
            }
        }
        return nodeList;
    }

    public static void main(String[] args) {
        List<BaseTree> list = new ArrayList<BaseTree>();
        BaseTree root1 = new BaseTree();
        root1.setId("1");
        BaseTree child1 = new BaseTree();
        child1.setId("11");
        child1.setParentId("1");
        BaseTree child11 = new BaseTree();
        child11.setId("111");
        child11.setParentId("11");
        BaseTree root2 = new BaseTree();
        root2.setId("2");
        BaseTree child2 = new BaseTree();
        child2.setId("21");
        child2.setParentId("2");
        list.add(root1);
        list.add(child1);
        list.add(child11);
        list.add(root2);
        list.add(child2);
        List<BaseTree> treelist = formatTree(list, true);
        String json = JSONArray.toJSONString(treelist);
        System.out.println(json);
    }

}