package com.gjs.fixedassets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


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
