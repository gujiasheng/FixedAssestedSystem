package com.gjs.fixedassets.service;


import com.gjs.fixedassets.entity.Mymessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface MyMessageService {
    List<Mymessage> selectMessageLimit(Integer userId);

    List<Mymessage> selectMessagePage(@Param("userId") Integer userId, @Param("page") Integer page, @Param("limit") Integer limit);

    List<Mymessage> selectMessageCount(Integer userId);

    List<Mymessage> selectIsNewCount(@Param("receiver") Integer receiver);

    int updateIsNew(@Param("myMessageId") Integer myMessageId, @Param("isNew") Integer isNew);

    Mymessage selectMyMessage(Integer messId);


}
