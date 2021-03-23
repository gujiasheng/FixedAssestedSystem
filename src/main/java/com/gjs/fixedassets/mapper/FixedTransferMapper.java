package com.gjs.fixedassets.mapper;

import com.gjs.fixedassets.entity.FixedTransfer;
import com.gjs.fixedassets.entity.Fixedcard;
import com.gjs.fixedassets.service.FixedTransferService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface FixedTransferMapper {

    List<Fixedcard> selectFixedTransferByCompanyIdPage(@Param("companyId") Integer companyId, @Param("page") Integer page, @Param("limit") Integer limit, @Param("fixedId") String fixedId, @Param("fixedName") String fixedName);

    List<Fixedcard> selectFixedTransferCount(@Param("companyId") Integer companyId, @Param("fixedId") String fixedId, @Param("fixedName") String fixedName);

    FixedTransfer selectFixedTransByCidFid(@Param("companyId") Integer companyId, @Param("fixedcardId") Integer fixedcardId);

    List<FixedTransfer> selectFixedTransByCompanyId(@Param("companyId") Integer companyId);
}