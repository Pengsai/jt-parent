package com.jt.cart.mapper;

import com.jt.common.mapper.SysMapper;
import com.jt.dubbo.pojo.Cart;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CartMapper extends SysMapper<Cart> {

    /**
     * 修改商品数量
     * @param cart
     */
    int updateCartNum(@Param("cart") Cart cart);
}
