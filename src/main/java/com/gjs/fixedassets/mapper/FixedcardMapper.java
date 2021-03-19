package com.gjs.fixedassets.mapper;

import com.gjs.fixedassets.entity.Fixedcard;
import com.gjs.fixedassets.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Mapper
public interface FixedcardMapper {
    Fixedcard selectByPrimaryKey(Integer fixedcardId);

    //添加固资卡片
    void insertFixedAsssetCard(Fixedcard fixedcard);

    //根据companyid查询固定资产列表
    List<Fixedcard> selectFixedAsssetByCompany(Integer companyId);

    List<String> selectFixedIdList(Integer companyId);

    List<Fixedcard> selectFixedByCompanyIdPage(@Param("companyId") Integer companyId, @Param("page") Integer page, @Param("limit") Integer limit, @Param("fixedId") String fixedId, @Param("fixedName") String fixedName, @Param("useStatus") Integer useStatus);

    List<Fixedcard> selectFixedCount(@Param("companyId") Integer companyId, @Param("fixedId") String fixedId, @Param("fixedName") String fixedName, @Param("useStatus") Integer useStatus);


}