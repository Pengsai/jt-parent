package com.jt.dubbo.service;

import com.alibaba.dubbo.rpc.protocol.rest.support.ContentType;
import com.jt.common.vo.SysResult;
import com.jt.dubbo.pojo.Cart;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;


//给dubbo调用时注入，配置
@Path("/cart")		//http://cart.jt.com/cart/query/7
@Consumes({MediaType.APPLICATION_JSON, MediaType.TEXT_XML})
@Produces({ContentType.APPLICATION_JSON_UTF_8, ContentType.TEXT_XML_UTF_8})
public interface DubboCartRestService {
	//我的购物车
	@GET
	@Path("/query")
	List<Cart> myCartList(@QueryParam(value = "userId") Long userId);

/*	@POST
	@Path("/save")
	SysResult saveCart(
			@QueryParam(value = "userId") Long userId,
			@QueryParam(value = "itemId") Long itemId,
			@QueryParam(value = "itemTitle") String itemTitle,
			@QueryParam(value = "itemImage") String itemImage,
			@QueryParam(value = "itemPrice") Long itemPrice,
			@QueryParam(value = "num") Integer num);*/

	@POST
	@Path("/save")
	SysResult saveCart(Cart cart);


	@GET
	@Path("/update/num")
	SysResult updateCartNum(
			@QueryParam(value = "userId") Long userId,
			@QueryParam(value = "itemId") Long itemId,
			@QueryParam(value = "num") Integer num);

	@GET
	@Path("/delete")
	SysResult deleteCart(
			@QueryParam(value = "userId") Long userId,
			@QueryParam(value = "itemId") Long itemId);

}
