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
    }

    String  imageUrl = "http://image.jt.com/images/";

    String imagePath = "D:/jt-upload/images/";
}
