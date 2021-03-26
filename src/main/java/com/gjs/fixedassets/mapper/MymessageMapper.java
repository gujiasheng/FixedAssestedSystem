package com.gjs.fixedassets.mapper;

import com.gjs.fixedassets.entity.Mymessage;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface MymessageMapper {

    List<Mymessage> selectMessageLimit(Integer userId);

    List<Mymessage> selectMessagePage(@Param("userId") Integer userId, @Param("page") Integer page, @Param("limit") Integer limit);

    List<Mymessage> selectMessageCount(Integer userId);

    int addMyMessage(Mymessage mymessage);

    List<Mymessage> selectIsNewCount(@Param("receiver") Integer receiver);

    int updateIsNew(@Param("myMessageId") Integer myMessageId, @Param("isNew") Integer isNew);


}