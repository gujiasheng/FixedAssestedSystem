package com.gjs.fixedassets.controller;

import com.gjs.fixedassets.entity.*;
import com.gjs.fixedassets.mapper.CheckRecordStatusMapper;
import com.gjs.fixedassets.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.io.Console;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class MyFixedAssetTransferController {
    @Autowired
    private FixedcardService fixedcardService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private FixedTransferService fixedTransferService;

    @Autowired
    private CheckRecordStatusService checkRecordStatusService;

    @Autowired
    private MyMessageService myMessageService;
    /*
     * @Description TODO
     * 打开我的领用
     * @Author
     * @Date 2021-03-23
     * @params
     * @Return
     **/
    @GetMapping("/toMyFixedAssetTransfer")
    public String toMyFixedAssetTransfer(Model model, HttpSession session) {


        return "myfixedtransfer/MyFixedAssetTransfer";
    }

    /*
     * @Description TODO
     * 打开新建领用单
     * @Author
     * @Date 2021-03-23
     * @params
     * @Return
     **/
    @GetMapping("/toMyFixedAssetTransferAdd")
    public String toMyFixedAssetTransfer() {

        return "myfixedtransfer/MyFixedAssetTransferAdd";
    }

    /*
     * @Description TODO
     * 打开我的申请列表
     * @Author
     * @Date 2021-03-31
     * @params
     * @Return
     **/
    @GetMapping("/toMyFixedAssetTransferApplyList")
    public String toMyFixedAssetTransferApplyList() {
        return "myfixedtransfer/MyFixedAssetTransferApplyList";
    }


    /*
     * @Description TODO
     * 闲置资产列表
     * @Author
     * @Date 2021-03-23
     * @params
     * @Return
     **/
    @GetMapping("/selectFixedAssetTransferAdd")
    @ResponseBody
    public Map<String, Object> selectFixedAssetTransferAdd(@RequestParam(required = false, defaultValue = "1") Integer page,
                                                           @RequestParam(required = false, defaultValue = "5") Integer limit,
                                                           @RequestParam(required = false, defaultValue = "", value = "searchFixedId") String fixedId,
                                                           @RequestParam(required = false, defaultValue = "", value = "searchFixedName") String fixedName,
                                                           Model model, HttpSession session) {

        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());

        List<Fixedcard> fixedcardList = fixedTransferService.selectFixedTransferByCompanyIdPage(user.getCompanyId(), page, limit, fixedId, fixedName);
        model.addAttribute("fcl", fixedcardList);
        List<Fixedcard> allFixed = fixedTransferService.selectFixedTransferCount(user.getCompanyId(), fixedId, fixedName);
        int FixedCount = allFixed.size();
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "操作成功");
        map.put("count", FixedCount);
        map.put("data", fixedcardList);
//        System.out.println(map);
        return map;
    }


    /*
     * @Description TODO
     *
     * @Author
     * @Date 2021-03-23
     * @params
     * @Return
     **/
    @GetMapping("/toFixedTransferCheck{fixedcardId}")
    public String toFixedTransferCheck(Model model, HttpSession session, @PathVariable("fixedcardId") Integer fixedcardId) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        model.addAttribute("user", user);
        FixedTransfer fixedTransfer = fixedTransferService.selectFixedTransByCidFid(user.getCompanyId(), fixedcardId);
        String fixedTypeName = FixedType.getValueByCode(fixedTransfer.getFixedcard().getFixedtypeId());
        model.addAttribute("ftname", fixedTypeName);
        model.addAttribute("ft", fixedTransfer);
//        System.out.println(fixedTransfer);
        List<FixedTransfer> fixedTransfers = fixedTransferService.selectFixedTransByCompanyId(user.getCompanyId());
        Integer fixedTrId2 = 1000001;
        if (fixedTransfers.size() != 0) {
            fixedTrId2 = fixedTransfers.get(0).getFixedTransferId2() + 1;
        }

        model.addAttribute("ftid2", fixedTrId2);
        return "myfixedtransfer/FixedTransferCheck";
    }

    /*
     * @Description TODO
     * 前往领用申请2
     * @Author
     * @Date 2021-03-25
     * @params
     * @Return
     **/
    @GetMapping("/toFixedTransferCheck2{fixedTransId}/{messId}")
    public String toFixedTransferCheck2(Model model, HttpSession session,
                                        @PathVariable("fixedTransId") Integer fixedTransId,
                                        @PathVariable("messId") Integer messId) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        model.addAttribute("user", user);
        FixedTransfer fixedTransfer = fixedTransferService.selectFixedTransById(fixedTransId);
        String fixedTypeName = FixedType.getValueByCode(fixedTransfer.getFixedcard().getFixedtypeId());
        model.addAttribute("ftname", fixedTypeName);
        model.addAttribute("ft", fixedTransfer);
        CheckRecordStatus newCheckRecordStatus = checkRecordStatusService.selectNewNodeByRecordId(fixedTransId);
        model.addAttribute("ncrs", newCheckRecordStatus);
        model.addAttribute("messId", messId);

        List<String> remarkList = checkRecordStatusService.selectRemarkByRidNid(fixedTransId);

        //传到前端的四个remark
        Map<String, String> map = new HashMap<>();
        if (remarkList.size() == 2) {
            map.put("remark1", remarkList.get(1));
            map.put("remark2", "");
            map.put("remark3", "");
            map.put("remark4", "");
        } else if (remarkList.size() == 3) {
            map.put("remark1", remarkList.get(1));
            map.put("remark2", remarkList.get(2));
            map.put("remark3", "");
            map.put("remark4", "");
        } else if (remarkList.size() == 4) {
            map.put("remark1", remarkList.get(1));
            map.put("remark2", remarkList.get(2));
            map.put("remark3", remarkList.get(3));
            map.put("remark4", "");
        } else if (remarkList.size() == 5) {
            map.put("remark1", remarkList.get(1));
            map.put("remark2", remarkList.get(2));
            map.put("remark3", remarkList.get(3));
            map.put("remark4", remarkList.get(4));
        }

        model.addAttribute("map", map);
        return "myfixedtransfer/FixedTransferCheck2";
    }

    /*
     * @Description TODO
     * 提交领用申请
     * @Author
     * @Date 2021-03-31
     * @params
     * @Return
     **/
    @PostMapping("/checkTransferApply")
    @ResponseBody
    public void checkTransferApply(FixedTransfer fixedTransfer, Mymessage mymessage,
                                   CheckRecordStatus checkRecordStatus, HttpSession session,
                                   @RequestParam("personcharge") Integer personcharge,
                                   @RequestParam("fixedcardId1") Integer fixedcardId) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        //消息名称
        Fixedcard fixedcard = fixedcardService.selectFixedByFixedCardId(fixedcardId);
        fixedcard.setUseStatus(6);
        mymessage.setMessageTitle(fixedcard.getFixedName() + "领用申请");

        mymessage.setMessageContent(fixedTransfer.getFixedTransferId());
        mymessage.setMessageType(1);
        mymessage.setPromoter(user.getUserId());
        mymessage.setReceiver(personcharge);
        checkRecordStatus.setCheckMan(user.getUserId());
        checkRecordStatus.setCheckTypeId(1);
        checkRecordStatus.setCheckNodeId(1);
        fixedTransfer.setFixedcardId(fixedcardId);
        fixedTransfer.setUsePerson(user.getUserId());
        fixedTransfer.setCompanyId(user.getCompanyId());
        List<FixedTransfer> fixedTransfers = fixedTransferService.selectFixedTransByCompanyId(user.getCompanyId());
        Integer fixedTrId2 = 1000001;

        if (fixedTransfers.size() != 0) {
            fixedTrId2 = fixedTransfers.get(0).getFixedTransferId2() + 1;

        }
        fixedTransfer.setFixedTransferId2(fixedTrId2);
        fixedTransferService.applyTransfer(fixedTransfer, mymessage, checkRecordStatus, fixedcard);
    }


    /*
     * @Description TODO
     * 负责人审核通过
     * @Author
     * @Date 2021-03-25
     * @params
     * @Return
     **/
    @PostMapping("/applyAgree")
    @ResponseBody
    public void pcapply1(HttpSession session, @RequestParam("fixedcardId1") Integer fixedcardId,
                         @RequestParam("newRecordNode") Integer newRecordNode,
                         @RequestParam("messId") Integer messId,
                         @RequestParam("ft1") Integer recordId,
                         @RequestParam(required = false, value = "remark1") String remark1,
                         @RequestParam(required = false, value = "remark2") String remark2,
                         @RequestParam(required = false, value = "remark3") String remark3,
                         @RequestParam(required = false, value = "remark4") String remark4,
                         @RequestParam("useManId") Integer useManId
    ) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());

        Mymessage oldMyMessage = myMessageService.selectMyMessage(messId);
        Fixedcard fixedcard = fixedcardService.selectFixedByFixedCardId(fixedcardId);
        fixedcard.setUseStatus(6);
        CheckRecordStatus checkRecordStatus = new CheckRecordStatus();
        Mymessage newMyMessage = new Mymessage();

        checkRecordStatus.setCheckRecordId(recordId);//资产领用id
        checkRecordStatus.setCheckMan(user.getUserId());
        checkRecordStatus.setCheckTypeId(1);
        newMyMessage.setPromoter(user.getUserId());
        newMyMessage.setMessageContent(oldMyMessage.getMessageContent());
        newMyMessage.setMessageTitle(oldMyMessage.getMessageTitle());
        System.out.println(remark1);
        switch (newRecordNode) {
            case 1:
                checkRecordStatus.setCheckNodeId(2);
                checkRecordStatus.setRemark(remark1);
                newMyMessage.setReceiver(user.getDepartment().getDepartmentManager());
                fixedTransferService.applyTransfer1(fixedcard, oldMyMessage, checkRecordStatus, newMyMessage);
                break;
            case 2:
                checkRecordStatus.setCheckNodeId(3);
                newMyMessage.setReceiver(fixedcard.getPersonCharge());
                checkRecordStatus.setRemark(remark2);
                fixedTransferService.applyTransfer1(fixedcard, oldMyMessage, checkRecordStatus, newMyMessage);
                break;
            case 3:
                checkRecordStatus.setCheckNodeId(7);
                newMyMessage.setReceiver(useManId);
                checkRecordStatus.setRemark(remark3);
                fixedTransferService.applyTransfer1(fixedcard, oldMyMessage, checkRecordStatus, newMyMessage);
                break;
            case 7:
                checkRecordStatus.setCheckNodeId(8);
                fixedcard.setPersonCharge(useManId);
                checkRecordStatus.setRemark(remark4);
                newMyMessage.setReceiver(useManId);
                fixedcard.setUseStatus(2);
                User useman = userService.selectUserByUserId(useManId);
                fixedcard.setDepartmentId(useman.getDepartmentId());

                fixedTransferService.applyTransfer1(fixedcard, oldMyMessage, checkRecordStatus, newMyMessage);
                break;

        }

    }


    /*
     * @Description TODO
     * 不同意按钮
     * @Author
     * @Date 2021-03-30
     * @params
     * @Return
     **/
    @PostMapping("/applyDisAgree")
    @ResponseBody
    public void pcapply2(HttpSession session, @RequestParam("fixedcardId1") Integer fixedcardId,
                         @RequestParam("newRecordNode") Integer newRecordNode,
                         @RequestParam("messId") Integer messId,
                         @RequestParam("ft1") Integer recordId,
                         @RequestParam(required = false, value = "remark1") String remark1,
                         @RequestParam(required = false, value = "remark2") String remark2,
                         @RequestParam(required = false, value = "remark3") String remark3,
                         @RequestParam(required = false, value = "remark4") String remark4,
                         @RequestParam("useManId") Integer useManId
    ) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());

        Mymessage oldMyMessage = myMessageService.selectMyMessage(messId);
        Fixedcard fixedcard = fixedcardService.selectFixedByFixedCardId(fixedcardId);
        fixedcard.setUseStatus(1);
        CheckRecordStatus checkRecordStatus = new CheckRecordStatus();
        Mymessage newMyMessage = new Mymessage();

        checkRecordStatus.setCheckRecordId(recordId);//资产领用id
        checkRecordStatus.setCheckMan(user.getUserId());
        checkRecordStatus.setCheckTypeId(1);

        newMyMessage.setPromoter(user.getUserId());
        newMyMessage.setMessageContent(oldMyMessage.getMessageContent());
        newMyMessage.setMessageTitle(oldMyMessage.getMessageTitle());

        switch (newRecordNode) {
            case 1:
                checkRecordStatus.setCheckNodeId(5);
                checkRecordStatus.setRemark(remark1);
                newMyMessage.setReceiver(useManId);
                fixedTransferService.applyTransfer1(fixedcard, oldMyMessage, checkRecordStatus, newMyMessage);
                break;
            case 2:
                checkRecordStatus.setCheckNodeId(6);
                checkRecordStatus.setRemark(remark2);
                newMyMessage.setReceiver(fixedcard.getPersonCharge());
                fixedTransferService.applyTransfer1(fixedcard, oldMyMessage, checkRecordStatus, newMyMessage);
                break;
            case 3:
                checkRecordStatus.setCheckNodeId(9);
                checkRecordStatus.setRemark(remark3);
                newMyMessage.setReceiver(useManId);
                fixedTransferService.applyTransfer1(fixedcard, oldMyMessage, checkRecordStatus, newMyMessage);
                break;
            case 7:
                checkRecordStatus.setCheckNodeId(9);
                checkRecordStatus.setRemark(remark4);
                newMyMessage.setReceiver(useManId);
                fixedTransferService.applyTransfer1(fixedcard, oldMyMessage, checkRecordStatus, newMyMessage);
                break;
        }

    }
    /*
     * @Description TODO
     * 已阅按钮
     * @Author
     * @Date 2021-03-31
     * @params
     * @Return
     **/
    @PostMapping("/read")
    @ResponseBody
    public void read(@RequestParam("messId") Integer messId) {
        Mymessage oldMyMessage = myMessageService.selectMyMessage(messId);
        myMessageService.updateIsNew(oldMyMessage.getMessageId(), 3);
    }
    /*
     * @Description TODO
     * 我的领用流程表数据
     * @Author
     * @Date 2021-03-31
     * @params
     * @Return
     **/
    @GetMapping("/MyFixedAssetTransferApplyList")
    @ResponseBody
    public Map<String, Object> MyFixedAssetTransferApplyList(HttpSession session,
                                                             @RequestParam(required = false, defaultValue = "1") Integer page,
                                                             @RequestParam(required = false, defaultValue = "5") Integer limit) {

        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());

        List<FixedTransfer> selectMyTransferApplyList = fixedTransferService.selectMyTransferApplyList(user.getUserId());

        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "操作成功");
        map.put("count", selectMyTransferApplyList.size());
        map.put("data", selectMyTransferApplyList);
        return map;


    }

    /*
     * @Description TODO
     * 查看我的领用列表
     * @Author
     * @Date 2021-03-31
     * @params
     * @Return
     **/
    @GetMapping("/toMyTransferList")
    @ResponseBody
    public Map<String, Object> selectMyTransferList(HttpSession session,
                                                    @RequestParam(required = false, defaultValue = "1") Integer page,
                                                    @RequestParam(required = false, defaultValue = "5") Integer limit
    ) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());

        List<FixedTransfer> selectMyTransferList = fixedTransferService.selectMyTransferList(user.getUserId());
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "操作成功");
        map.put("count", selectMyTransferList.size());
        map.put("data", selectMyTransferList);
        return map;
    }


    /*
     * @Description TODO
     *  查看流程单详情
     * @Author
     * @Date 2021-03-31
     * @params
     * @Return
     **/
    @GetMapping("/FixedTransferCheck3{fixedTransId}")
    public String FixedTransferCheck3(Model model, HttpSession session,
                                      @PathVariable("fixedTransId") Integer fixedTransId) {

        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        model.addAttribute("user", user);
        FixedTransfer fixedTransfer = fixedTransferService.selectFixedTransById(fixedTransId);
        String fixedTypeName = FixedType.getValueByCode(fixedTransfer.getFixedcard().getFixedtypeId());
        model.addAttribute("ftname", fixedTypeName);
        model.addAttribute("ft", fixedTransfer);
        CheckRecordStatus newCheckRecordStatus = checkRecordStatusService.selectNewNodeByRecordId(fixedTransId);
        model.addAttribute("ncrs", newCheckRecordStatus);


        return "myfixedtransfer/FixedTransferCheck3";
    }

}
