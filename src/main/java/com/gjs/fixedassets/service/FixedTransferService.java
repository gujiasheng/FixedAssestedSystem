package com.gjs.fixedassets.service;

import com.gjs.fixedassets.entity.CheckRecordStatus;
import com.gjs.fixedassets.entity.FixedTransfer;
import com.gjs.fixedassets.entity.Fixedcard;
import com.gjs.fixedassets.entity.Mymessage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FixedTransferService {

    List<Fixedcard> selectFixedTransferByCompanyIdPage(@Param("companyId") Integer companyId, @Param("page") Integer page, @Param("limit") Integer limit, @Param("fixedId") String fixedId, @Param("fixedName") String fixedName);


    List<Fixedcard> selectFixedTransferCount(@Param("companyId") Integer companyId, @Param("fixedId") String fixedId, @Param("fixedName") String fixedName);

    FixedTransfer selectFixedTransByCidFid(@Param("companyId") Integer companyId, @Param("fixedcardId") Integer fixedcardId);

    List<FixedTransfer> selectFixedTransByCompanyId(@Param("companyId") Integer companyId);

    void applyTransfer(FixedTransfer fixedTransfer, Mymessage mymessage, CheckRecordStatus checkRecordStatus);
}
