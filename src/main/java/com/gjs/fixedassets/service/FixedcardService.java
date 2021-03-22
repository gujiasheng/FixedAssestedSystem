package com.gjs.fixedassets.service;

import com.gjs.fixedassets.entity.Fixedcard;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

import java.util.List;


public interface FixedcardService {

    //添加固资卡片
    void insertFixedAsssetCard(Fixedcard fixedcard);

    List<Fixedcard> selectFixedAsssetByCompany(Integer companyId);

    List<String> selectFixedIdList(Integer companyId);

    List<Fixedcard> selectFixedByCompanyIdPage(@Param("companyId") Integer companyId, @Param("page") Integer page, @Param("limit") Integer limit, @Param("fixedId") String fixedId, @Param("fixedName") String fixedName, @Param("useStatus") Integer useStatus);

    List<Fixedcard> selectFixedCount(@Param("companyId") Integer companyId, @Param("fixedId") String fixedId, @Param("fixedName") String fixedName, @Param("useStatus") Integer useStatus);

    Fixedcard selectFixedByFixedCardId(@Param("fixedCardId") Integer fixedCardId);

    void updateFixedByFixedCardId(Fixedcard fixedCard);
}
