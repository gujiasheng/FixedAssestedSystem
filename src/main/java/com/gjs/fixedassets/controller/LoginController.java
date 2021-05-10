package com.gjs.fixedassets.controller;

import com.gjs.fixedassets.entity.*;
import com.gjs.fixedassets.mapper.CompanyindustryMapper;
import com.gjs.fixedassets.mapper.CompanynatureMapper;
import com.gjs.fixedassets.service.*;
import com.gjs.fixedassets.service.impl.MyMessageServiceImpl;
import jdk.nashorn.internal.runtime.Debug;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.net.ssl.HttpsURLConnection;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {
    @Autowired
    private UserService userService;
    @Autowired
    private CompanyindustryMapper companyindustryMapper;
    @Autowired
    private CompanynatureMapper companynatureMapper;
    @Autowired
    private CompanyService companyService;

    @GetMapping("/tologin")
    public String toLogin(Model model) {
        List<Company> list = companyService.selectAllCompany();
        model.addAttribute("list", list);
        return "login";
    }

    @GetMapping("/tologin2")
    public String toLogin2(Model model) {
        model.addAttribute("msg", "用户名或者密码有误，请重新登录");

        List<Company> list = companyService.selectAllCompany();
        model.addAttribute("list", list);
        return "login";
    }

    @GetMapping("/tologin3")
    public String toLogin3(Model model) {
        model.addAttribute("msg", "该账号已停用,请联系管理员启用");

        List<Company> list = companyService.selectAllCompany();
        model.addAttribute("list", list);
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
    public String login(@RequestParam("userName") String userName, @RequestParam("password") String password,
                        Integer companyId, Model model, HttpSession session) {

        User user = userService.selectUserByNamePSW(userName, password, companyId);


        if (user != null && !("").equals(user) && user.getIsStatus() == 1) {//1为账号启用
            session.setAttribute("user", user);
            return "redirect:/toheadleft";
        } else if (user != null && !("").equals(user) && user.getIsStatus() == 2) {//2为账号停用
            return "redirect:/tologin3";
        } else {
            return "redirect:/tologin2";
        }

    }

    @GetMapping("/toregister")
    public String toRegister(Model model) {
        List<Companyindustry> companyindustries = companyindustryMapper.selectIndustry();
        model.addAttribute("cis", companyindustries);
        List<Companynature> companynatures = companynatureMapper.selectCompanyNature();
        model.addAttribute("cns", companynatures);
        return "register";
    }

    @PostMapping("/registercompany")
    public String registerCompany(Company company, Department department, User user, Job job) {

        companyService.registerCompany(company, department, user, job);
        return "redirect:/tologin";
    }


    @GetMapping("/toheadleft")
    public String toheadleft(Model model, HttpSession session) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        model.addAttribute("u", user);
        return "/common/head-left-layout";
    }

    @GetMapping("/exit")
    public String toexit(HttpSession session) {
        session.setAttribute("user", null);
        return "redirect:/tologin";
    }


}
