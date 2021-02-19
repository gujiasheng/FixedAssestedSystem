package com.gjs.fixedassets.controller;

import com.gjs.fixedassets.entity.TestUser;
import com.gjs.fixedassets.service.TestUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class TestUserController {

    @Autowired
    private TestUserService testUserService;

    @ResponseBody
    @GetMapping("/findAllTest")
    public Map<String, Object> findAllTest() {
        List<TestUser> testUserList = testUserService.findAll();//查询所有用户


        //layui的table默认规定的数据格式
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "操作成功");
        map.put("count", 0);
        map.put("data", testUserList);
//        System.out.println(map);
        return map;
    }
}
