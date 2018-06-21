package com.jt.common.vo;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCatResult {

    @JsonProperty("data")	//json序列化时指定字段名称
    private List<ItemCatData> itemCats = new ArrayList<ItemCatData>();


}
