package com.gjs.fixedassets.controller;

import com.gjs.fixedassets.entity.Department;
import com.gjs.fixedassets.entity.Job;
import com.gjs.fixedassets.entity.Role;
import com.gjs.fixedassets.entity.User;
import com.gjs.fixedassets.service.DepartmentService;
import com.gjs.fixedassets.service.JobService;
import com.gjs.fixedassets.service.RoleService;
import com.gjs.fixedassets.service.UserService;
import jdk.nashorn.internal.runtime.Debug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private JobService jobService;

    @GetMapping("/tologin")
    public String toLogin() {
        return "login";
    }

    /*
     * @Description TODO
     * 登录功能
     * @Author
     * @Date 2021-02-22
     * @params
     * @Return
     **/
    @PostMapping("/login")
    public String login(@RequestParam("userName") String userName, @RequestParam("password") String password, Model model) {
        User user = userService.selectUserByNamePSW(userName, password);
        if (user != null && !("").equals(user)) {
            return "/common/head-left-layout";
        } else {
            model.addAttribute("msg", "用户名或者密码有误，请重新登录");
            return "login";
        }

    }

    @GetMapping("/toregister")
    public String toRegister() {
        return "register";
    }

    @GetMapping("/toheadleft")
    public String toheadleft() {
        return "/common/head-left-layout";
    }


}
