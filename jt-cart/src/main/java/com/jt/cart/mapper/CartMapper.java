package com.jt.cart.mapper;

import com.jt.cart.pojo.Cart;
import com.jt.common.mapper.SysMapper;
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
