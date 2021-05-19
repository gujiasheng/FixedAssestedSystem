package com.gjs.fixedassets.mapper;

import com.gjs.fixedassets.entity.CheckRecordStatus;
import com.gjs.fixedassets.entity.FixedTransfer;
import com.gjs.fixedassets.entity.Fixedcard;
import com.gjs.fixedassets.service.FixedTransferService;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.boot.autoconfigure.web.WebProperties;

import java.util.Date;
import java.util.List;

@Mapper
public interface FixedTransferMapper {

    List<Fixedcard> selectFixedTransferByCompanyIdPage(@Param("companyId") Integer companyId, @Param("page") Integer page, @Param("limit") Integer limit, @Param("fixedId") String fixedId, @Param("fixedName") String fixedName);

    List<Fixedcard> selectFixedTransferCount(@Param("companyId") Integer companyId, @Param("fixedId") String fixedId, @Param("fixedName") String fixedName);

    FixedTransfer selectFixedTransByCidFid(@Param("companyId") Integer companyId, @Param("fixedcardId") Integer fixedcardId);

    List<FixedTransfer> selectFixedTransByCompanyId(@Param("companyId") Integer companyId);

    int insert(FixedTransfer fixedTransfer);

    FixedTransfer selectFixedTransById(Integer fixedtransferId);

    List<FixedTransfer> selectMyTransferList(Integer usePerson);

    List<FixedTransfer> selectMyTransferApplyList(Integer usePerson);

    //查询某个固定资产的所有领用记录
//    List<FixedTransfer> selectAllTransferRecordBycardId(@Param("companyId") Integer companyId, @Param("fixedcardId") Integer fixedcardId, @Param("page") Integer page, @Param("limit") Integer limit, @Param("startdate") Date startdate, @Param("enddate") Date enddate, @Param("checkNode") Integer checkNode);
    List<FixedTransfer> selectAllTransferRecordBycardId(@Param("companyId") Integer companyId, @Param("fixedcardId") Integer fixedcardId, @Param("page") Integer page, @Param("limit") Integer limit, String startdate, String enddate, @Param("checkNode") Integer checkNode);


    //查询所有数量
//    List<FixedTransfer> selectAllCountTransferRecordBycardId(@Param("companyId") Integer companyId, @Param("fixedcardId") Integer fixedcardId, @Param("startdate") Date startdate, @Param("enddate") Date enddate, @Param("checkNode") Integer checkNode);
    List<FixedTransfer> selectAllCountTransferRecordBycardId(@Param("companyId") Integer companyId, @Param("fixedcardId") Integer fixedcardId, String startdate, String enddate, @Param("checkNode") Integer checkNode);
}