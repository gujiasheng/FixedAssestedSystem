package com.gjs.fixedassets.service.impl;

import com.gjs.fixedassets.entity.CheckRecordStatus;
import com.gjs.fixedassets.entity.FixedTransfer;
import com.gjs.fixedassets.entity.Fixedcard;
import com.gjs.fixedassets.entity.Mymessage;
import com.gjs.fixedassets.mapper.CheckRecordStatusMapper;
import com.gjs.fixedassets.mapper.FixedTransferMapper;
import com.gjs.fixedassets.mapper.FixedcardMapper;
import com.gjs.fixedassets.mapper.MymessageMapper;
import com.gjs.fixedassets.service.FixedTransferService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class FixedTransferServiceImpl implements FixedTransferService {
    @Resource
    private FixedTransferMapper fixedTransferMapper;
    @Resource
    private MymessageMapper mymessageMapper;

    @Resource
    private CheckRecordStatusMapper checkRecordStatusMapper;

    @Resource
    private FixedcardMapper fixedcardMapper;

    @Override
    public List<Fixedcard> selectFixedTransferByCompanyIdPage(Integer companyId, Integer page, Integer limit, String fixedId, String fixedName) {
        int startNum = (page - 1) * limit;
        return fixedTransferMapper.selectFixedTransferByCompanyIdPage(companyId, startNum, limit, fixedId, fixedName);
    }

    @Override
    public List<Fixedcard> selectFixedTransferCount(Integer companyId, String fixedId, String fixedName) {
        return fixedTransferMapper.selectFixedTransferCount(companyId, fixedId, fixedName);
    }

    @Override
    public FixedTransfer selectFixedTransByCidFid(Integer companyId, Integer fixedcardId) {
        return fixedTransferMapper.selectFixedTransByCidFid(companyId, fixedcardId);
    }

    @Override
    public List<FixedTransfer> selectFixedTransByCompanyId(Integer companyId) {
        return fixedTransferMapper.selectFixedTransByCompanyId(companyId);
    }

    @Override
    public void applyTransfer(FixedTransfer fixedTransfer, Mymessage mymessage, CheckRecordStatus checkRecordStatus, Fixedcard fixedcard) {
        fixedcardMapper.updateFixedByFixedCardId(fixedcard);

        fixedTransferMapper.insert(fixedTransfer);
        mymessage.setMessageContent(fixedTransfer.getFixedTransferId());

        mymessageMapper.addMyMessage(mymessage);

        checkRecordStatus.setCheckRecordId(fixedTransfer.getFixedTransferId());
        checkRecordStatusMapper.insert(checkRecordStatus);

    }

    @Override
    public FixedTransfer selectFixedTransById(Integer fixedtransferId) {
        return fixedTransferMapper.selectFixedTransById(fixedtransferId);
    }

    @Override
    public void applyTransfer1(Fixedcard fixedcard, Mymessage oldMyMessage, CheckRecordStatus checkRecordStatus, Mymessage newMyMessage) {

        fixedcardMapper.updateFixedByFixedCardId(fixedcard);

        mymessageMapper.updateIsNew(oldMyMessage.getMessageId(), 3);

        checkRecordStatusMapper.insert(checkRecordStatus);

        mymessageMapper.addMyMessage(newMyMessage);


    }

    @Override
    public void applyTransfer2(Fixedcard fixedcard, Mymessage oldMyMessage, CheckRecordStatus checkRecordStatus) {
        fixedcardMapper.updateFixedByFixedCardId(fixedcard);

        mymessageMapper.updateIsNew(oldMyMessage.getMessageId(), 3);

        checkRecordStatusMapper.insert(checkRecordStatus);
    }

    @Override
    public List<FixedTransfer> selectMyTransferList(Integer usePerson) {
        return fixedTransferMapper.selectMyTransferList(usePerson);
    }

}
