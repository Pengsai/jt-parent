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
}
