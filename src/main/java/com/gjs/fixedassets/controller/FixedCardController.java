package com.gjs.fixedassets.controller;

import com.gjs.fixedassets.entity.*;
import com.gjs.fixedassets.service.DepartmentService;
import com.gjs.fixedassets.service.FixedcardService;
import com.gjs.fixedassets.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class FixedCardController {
    @Autowired
    private FixedcardService fixedcardService;

    @Autowired
    private DepartmentService departmentService;

    @Autowired
    private UserService userService;


    /*
     * @Description TODO
     *  前往资产卡片添加页面
     * @Author
     * @Date 2021-03-15
     * @params
     * @Return
     **/
    @GetMapping("/tofixedcardadd")
    public String toFixedCardAdd(Model model, HttpSession session) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        Map<Integer, String> typeMap = FixedType.toList();
        model.addAttribute("typeMap", typeMap);
        Map<Integer, String> statusMap = FixedStatus.toStatusMap();
        model.addAttribute("sm", statusMap);
        Map<Integer, String> increMap = Increment.toIncreMap();
        model.addAttribute("im", increMap);
        List<Department> departmentList = departmentService.selectDepartmentByCompanyId2(user.getCompanyId());
        model.addAttribute("de", departmentList);
        List<User> userList = userService.selectAllUserByCompanyId(user.getCompanyId());
        model.addAttribute("ul", userList);

        Map<Integer, String> unitMap = Unit.toUnitMap();
        model.addAttribute("un", unitMap);
        model.addAttribute("companyName", user.getCompany().getCompanyName());

        List<String> fixedcards = fixedcardService.selectFixedIdList(user.getCompanyId());
        model.addAttribute("fds", fixedcards);

        //格式化录入时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        model.addAttribute("nowDateVal", formatter.format(date));
        model.addAttribute("nowDateText", formatter.format(date).toString());
        model.addAttribute("maker", user.getUserName());

        return "fixedassetcard/FixedAssetCardAdd";
    }

    /*
     * @Description TODO
     *  添加资产卡片
     * @Author
     * @Date 2021-03-15
     * @params
     * @Return
     **/
    @PostMapping("/fixedcardadd")
    public String fixedCardAdd(Fixedcard fixedcard, HttpSession session) {
        Object obj = session.getAttribute("user");
        User loginUser = (User) obj;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        fixedcard.setCompanyId(user.getCompanyId());

        Date date = new Date(System.currentTimeMillis());
        fixedcard.setEntryDate(date);
        fixedcardService.insertFixedAsssetCard(fixedcard);
        return "redirect:/tofixedcardadd";
    }

    /*
     * @Description TODO
     * 前往固定资产卡片列表
     * @Author
     * @Date 2021-03-19
     * @params
     * @Return
     **/
    @GetMapping("/toFixedAssetCardList")
    public String toFixedAssetCardList() {

        return "fixedassetcard/FixedAssetCardList";
    }

    /*
     * @Description TODO
     * 查询固定资产卡片列表
     * @Author
     * @Date 2021-03-19
     * @params
     * @Return
     **/
    @ResponseBody
    @GetMapping("/selectFixedByCompanyIdPage")
    public Map<String, Object> selectFixedByCompanyIdPage(@RequestParam(required = false, defaultValue = "1") Integer page,
                                                          @RequestParam(required = false, defaultValue = "10") Integer limit,
                                                          @RequestParam(required = false, defaultValue = "", value = "searchFixedId") String fixedId,
                                                          @RequestParam(required = false, defaultValue = "", value = "searchFixedName") String fixedName,
                                                          @RequestParam(required = false, defaultValue = "", value = "searchStatusId") Integer useStatus,
                                                          HttpSession session, Model model) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        List<Fixedcard> pageFixed = fixedcardService.selectFixedByCompanyIdPage(user.getCompanyId(), page, limit, fixedId, fixedName, useStatus);
        //获取总数据数量
        List<Fixedcard> allFixed = fixedcardService.selectFixedCount(user.getCompanyId(), fixedId, fixedName, useStatus);
        int FixedCount = allFixed.size();
        //用layui的table渲染数据的json有格式要求，需要封装一下
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "操作成功");
        map.put("count", FixedCount);
        map.put("data", pageFixed);
//        System.out.println(map);
        return map;
    }

}
