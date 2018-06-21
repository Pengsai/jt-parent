package com.jt.common.vo;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemCatData {
	//序列化成json数据时为 u
	@JsonProperty("u")
	private String url;
	
	@JsonProperty("n")
	private String name;
	
	@JsonProperty("i")
	private List<?> items;
	

}
