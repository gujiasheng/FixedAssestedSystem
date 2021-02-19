package com.gjs.fixedassets.controller;

import com.gjs.fixedassets.entity.User;
import com.gjs.fixedassets.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class UserController {
    @Resource
    private UserService userService;

    @ResponseBody
    @GetMapping("/selectAllUserByCompanyId")
    public Map<String, Object> selectAllUserByCompanyId() {
        List<User> allUser = userService.selectByCompanyId(1);

        //用layui的table渲染数据的json有格式要求，需要封装一下
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "操作成功");
        map.put("count", 0);
        map.put("data", allUser);
        return map;
    }


}
