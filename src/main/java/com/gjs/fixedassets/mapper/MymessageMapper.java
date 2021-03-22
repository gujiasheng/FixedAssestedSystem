package com.gjs.fixedassets.mapper;

import com.gjs.fixedassets.entity.Mymessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface MymessageMapper {

    List<Mymessage> selectMessageLimit(Integer userId);


}