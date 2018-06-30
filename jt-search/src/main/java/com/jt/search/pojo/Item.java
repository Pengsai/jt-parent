package com.jt.search.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.solr.client.solrj.beans.Field;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.jt.common.po.BasePojo;

//商品信息
@JsonIgnoreProperties(ignoreUnknown = true) //忽略未知字段
@Getter
@Setter
@ToString
public class Item extends BasePojo{
	//将solr中的数据与pojo对象进行映射
	@Field("id")
	private Long id;  //商品的编号
	
	@Field("title")
	private String title; //商品的标题
	
	@Field("sellPoint")
	private String sellPoint;  //商品的卖点
	
	@Field("price")
	private Long price;			//商品的价格
	
	@Field("num")
	private Integer num;		//商品数量
	private String barcode;		//条形码
	
	@Field("image")
	private String image;		//商品图片url信息
	private Long cid;			//通过ajax发起请求  商品分类id号
	private Integer status;		//1正常，2下架，3删除'
	private String[] images;    //为了实现前台图片回显添加的属性
	
	

	//每一个的图片信息
	public String[] getImages(){
		if(image !=null){
			return image.split(",");
		}else{
			return null;
		}
	}
	
}
