package com.jt.jsoup.pojo;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.jt.common.po.BasePojo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Table(name = "tb_item_jsoup")
@Getter
@Setter
@ToString
public class Item extends BasePojo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;    //主键Id
    private Long itemId;    //商品Id
    private String title;    //商品的标题
    private String sellPoint;    //商品的卖点
    private Long price;        //价格
    private String image;    //商品的图片信息

}
