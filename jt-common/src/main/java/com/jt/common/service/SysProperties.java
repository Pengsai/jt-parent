package com.jt.common.service;

/**
 * 数据字典
 */
public interface SysProperties {

    interface itemStatus {
        /**
         * 下架
         */
        Integer instock = 2;

        /**
         * 上架
         */
        Integer reshelf = 1;

        /**
         * 删除
         */
        Integer delete = 3;

    }

    /**
     * 登陆方式
     */
    interface loginType {
        /**
         * 用户名
         */
        int username = 1;

        /**
         * 手机号
         */
        int phone = 2;

        /**
         * 邮箱
         */
        int email = 3;
    }

    interface interUrl {
        /**
         * 注册
         */
        String registerUrl = "http://sso.jt.com/user/register";

        /**
         * 登陆
         */
        String loginUrl = "http://sso.jt.com/user/login";

        /**
         * 去购物车
         */
        String toCartUrl = "http://cart.jt.com/cart/query/";

        /**
         * 新增购物车
         */
        String saveCartUrl = "http://cart.jt.com/cart/save";

        /**
         * 修改购物车商品数量
         */
        String saveCartNumUrl = "http://cart.jt.com/cart/update/num/";

        /**
         * 删除购物车
         */
        String deleteCartUrl = "http://cart.jt.com/cart/delete/";

        /**
         * solr搜索
         */
        String searchUrl = "http://search.jt.com/search";


    }

    String  imageUrl = "http://image.jt.com/images/";

    String imagePath = "D:/jt-upload/images/";
}
