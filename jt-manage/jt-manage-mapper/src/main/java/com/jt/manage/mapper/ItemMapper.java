package com.jt.manage.mapper;

import com.jt.common.mapper.SysMapper;
import com.jt.manage.pojo.Item;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemMapper extends SysMapper<Item>{

    List<Item> findItemList(@Param("begin") int begin,
                            @Param("rows") int rows);

    String findItemName(@Param("cid") Long cid);
}
