package com.jt.common.po;

/**
 * @ClassName BaseTree
 * @Description TODO
 * @Author PS
 * @Date 2018/6/13 14:48
 **/

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

/**
 * treegrid树形表格基础对象
 */
@Setter
@Getter
public class BaseTree implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = -9189631784252440402L;

    public String id;//节点id

    public String parentId;//节点父id

    @JsonProperty("u")
    public String value1;//

    @JsonProperty("n")
    public String  value2;

    @JsonProperty("i")
    public  List<?> value3;

    public Boolean leaf = true;//是否为叶子节点，true表示是叶子节点，false表示不是叶子节点

    public Boolean expanded = true; //是否展开，默认true,展开

    public List<BaseTree> children;//孩子节点


    public BaseTree() {

    }

    public BaseTree(String id, String parentId) {
        this.id = id;
        this.parentId = parentId;
    }


}
