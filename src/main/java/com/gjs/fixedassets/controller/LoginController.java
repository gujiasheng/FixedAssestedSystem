package com.gjs.fixedassets.controller;

import com.gjs.fixedassets.entity.Department;
import com.gjs.fixedassets.entity.Job;
import com.gjs.fixedassets.entity.Role;
import com.gjs.fixedassets.service.DepartmentService;
import com.gjs.fixedassets.service.JobService;
import com.gjs.fixedassets.service.RoleService;
import com.gjs.fixedassets.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

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

    @GetMapping("/toregister")
    public String toRegister() {
        return "register";
    }

    @GetMapping("/toheadleft")
    public String toheadleft() {
        return "/common/head-left-layout";
    }

    @GetMapping("/touserlist")
    public String touserlist(Model model) {
        List<Department> departmentList = departmentService.selectDepartmentByCompanyId(1);
        model.addAttribute("departmentList", departmentList);
        List<Role> roleList = roleService.selectAllRole();
        model.addAttribute("roleList", roleList);
        List<Job> jobList = jobService.selectAllJobByCompanyId(1);
        model.addAttribute("jobList", jobList);
        return "/admin/userlist";
    }


}
