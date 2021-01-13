package com.gjs.fixedassets.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {


    @GetMapping("tologin")
    public String toLogin() {
        return "login";
    }
}
