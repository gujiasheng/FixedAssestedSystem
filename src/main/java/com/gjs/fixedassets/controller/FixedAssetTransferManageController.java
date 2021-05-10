package com.gjs.fixedassets.controller;

import com.gjs.fixedassets.entity.*;
import com.gjs.fixedassets.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class FixedAssetTransferManageController {
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

    @GetMapping("/toFixedAssetTransferManageList")
    public String toMyFixedAssetTransferApplyList() {
        return "fixedassettransfermanage/FixedAssetTransferManageList";
    }

}
