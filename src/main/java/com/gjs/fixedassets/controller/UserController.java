package com.gjs.fixedassets.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gjs.fixedassets.entity.User;
import com.gjs.fixedassets.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class UserController {
    @Resource
    private UserService userService;


    /*
     * @Description TODO
     * 根据公司id查询该公司所有人
     * @Author
     * @Date 2021-02-19
     * @params
     * @Return
     **/
    @ResponseBody
    @GetMapping("/selectAllUserByCompanyId")
    public Map<String, Object> selectAllUserByCompanyId(@RequestParam(required = false, defaultValue = "1") Integer page,
                                                        @RequestParam(required = false, defaultValue = "10") Integer limit,
                                                        @RequestParam(required = false, defaultValue = "", value = "searchUserName") String userName,//查询条件
                                                        @RequestParam(required = false, defaultValue = "", value = "searchUserPhone") Integer phone,
                                                        @RequestParam(required = false, defaultValue = "", value = "searchdepartmentId") Integer departmentId,
                                                        @RequestParam(required = false, defaultValue = "", value = "searchroleId") Integer roleId,
                                                        @RequestParam(required = false, defaultValue = "", value = "searchIsStatus") Integer isStatus) {
        List<User> pageUser = userService.selectByCompanyId(1, page, limit, userName, phone, departmentId, roleId, isStatus);//每页显示的数据
        //获取总数据数量
        List<User> allUser = userService.selectAllUserCount(1, userName, phone, departmentId, roleId, isStatus);
        int userCount = allUser.size();
        //用layui的table渲染数据的json有格式要求，需要封装一下
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "操作成功");
        map.put("count", userCount);
        map.put("data", pageUser);
//        System.out.println(map);
        return map;
    }


}
