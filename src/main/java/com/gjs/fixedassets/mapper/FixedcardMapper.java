package com.gjs.fixedassets.mapper;

import com.gjs.fixedassets.entity.Fixedcard;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FixedcardMapper {
    Fixedcard selectByPrimaryKey(Integer fixedcardId);

    //添加固资卡片
    void insertFixedAsssetCard(Fixedcard fixedcard);
}