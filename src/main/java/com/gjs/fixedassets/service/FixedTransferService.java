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

    void applyTransfer(FixedTransfer fixedTransfer, Mymessage mymessage, CheckRecordStatus checkRecordStatus, Fixedcard fixedcard);

    FixedTransfer selectFixedTransById(Integer fixedtransferId);

    /*
     * @Description TODO
     * 领用审核通过按钮接口服务类
     * @Author
     * @Date 2021-03-29
     * @params fixedcard修改固定资产当前状态为申请中，oldMyMessage让同意后的消息消失，checkRecordStatus添加审核状态，newMyMessage给下一个节点对象添加新的消息
     * @Return
     **/
    void applyTransfer1(Fixedcard fixedcard, Mymessage oldMyMessage, CheckRecordStatus checkRecordStatus, Mymessage newMyMessage);

    /*
     * @Description TODO
     * 点击已领取后修改卡片，插入审核流
     * @Author
     * @Date 2021-03-29
     * @params
     * @Return
     **/
    void applyTransfer2(Fixedcard fixedcard, Mymessage oldMyMessage, CheckRecordStatus checkRecordStatus);


    List<FixedTransfer> selectMyTransferList(Integer usePerson);

    List<FixedTransfer> selectMyTransferApplyList(Integer usePerson);


}
