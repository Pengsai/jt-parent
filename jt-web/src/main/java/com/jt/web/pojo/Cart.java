package com.jt.web.pojo;

import com.jt.common.po.BasePojo;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
public class Cart extends BasePojo {

    private Long id;            //购物车ID  主键自增
    private Long userId;        //用户ID
    private Long itemId;        //商品的ID
    private String itemTitle;    //商品的标题
    private String itemImage;    //商品的图片信息
    private Long itemPrice;    //商品的价格
    private Integer num;        //商品数量


}
