package com.gjs.fixedassets.controller;

import com.gjs.fixedassets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @GetMapping("/tologin")
    public String toLogin() {
        return "login";
    }

    @GetMapping("/toregister")
    public String toRegister() {
        return "register";
    }

    @GetMapping("/toheadleft")
    public String toheadleft() {
        return "/common/head-left-layout";
    }

    @GetMapping("/touserlist")
    public String touserlist() {

        return "/admin/userlist";
    }


}
