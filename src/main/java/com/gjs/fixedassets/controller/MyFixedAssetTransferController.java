package com.gjs.fixedassets.controller;

import com.gjs.fixedassets.entity.*;
import com.gjs.fixedassets.service.DepartmentService;
import com.gjs.fixedassets.service.FixedTransferService;
import com.gjs.fixedassets.service.FixedcardService;
import com.gjs.fixedassets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.Console;
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
     * 闲置资产列表
     * @Author
     * @Date 2021-03-23
     * @params
     * @Return
     **/
    @GetMapping("/selectFixedAssetTransferAdd")
    @ResponseBody
    public Map<String, Object> selectFixedAssetTransferAdd(@RequestParam(required = false, defaultValue = "1") Integer page,
                                                           @RequestParam(required = false, defaultValue = "10") Integer limit,
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
        System.out.println(fixedTransfer);
        List<FixedTransfer> fixedTransfers = fixedTransferService.selectFixedTransByCompanyId(user.getCompanyId());
        Integer fixedTrId2 = 1000001;
        if (fixedTransfers != null) {
            fixedTrId2 = fixedTransfers.get(0).getFixedTransferId2() + 1;
        }

        model.addAttribute("ftid2", fixedTrId2);
        return "myfixedtransfer/FixedTransferCheck";
    }

    @PostMapping("/checkTransferApply")
    @ResponseBody
    public void checkTransferApply(FixedTransfer fixedTransfer, Mymessage mymessage, CheckRecordStatus checkRecordStatus, HttpSession session, @RequestParam("personcharge") Integer personcharge) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        mymessage.setMessageTitle("固定资产领用申请");
        mymessage.setMessageContent(fixedTransfer.getFixedTransferId());
        mymessage.setMessageType(1);
        mymessage.setPromoter(user.getUserId());
        mymessage.setReceiver(personcharge);
        checkRecordStatus.setCheckMan(user.getUserId());
        checkRecordStatus.setCheckTypeId(1);
        checkRecordStatus.setCheckNodeId(1);
        fixedTransferService.applyTransfer(fixedTransfer, mymessage, checkRecordStatus);
    }
}
