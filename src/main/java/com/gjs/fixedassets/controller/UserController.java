package com.gjs.fixedassets.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gjs.fixedassets.entity.*;
import com.gjs.fixedassets.mapper.UserMapper;
import com.gjs.fixedassets.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private RoleService roleService;
    @Autowired
    private JobService jobService;
    @Autowired
    private FixedcardService fixedcardService;

    /*
     * @Description TODO
     * 前往人员管理页面
     * @Author
     * @Date 2021-02-22
     * @params
     * @Return
     **/
    @GetMapping("/touserlist")
    public String touserlist(Model model, HttpSession session) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        List<Department> departmentList = departmentService.selectDepartmentByCompanyId2(user.getCompanyId());
        model.addAttribute("departmentList", departmentList);
        List<Role> roleList = roleService.selectAllRole();
        model.addAttribute("roleList", roleList);
        List<Job> jobList = jobService.selectAllJobByCompanyId(user.getCompanyId());
        model.addAttribute("jobList", jobList);
        Integer qiyong = 1;
        Integer tingyong = 2;
        model.addAttribute("qiyong", qiyong);
        model.addAttribute("tingyong", tingyong);


        return "/admin/userlist";
    }

    /*
     * @Description TODO
     * 打开人员添加页面
     * @Author
     * @Date 2021-02-22
     * @params
     * @Return
     **/
    @GetMapping("/touseradd")
    public String touseradd(Model model, HttpSession session) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        List<Department> departmentList = departmentService.selectDepartmentByCompanyId2(user.getCompanyId());
        model.addAttribute("departmentList", departmentList);
        List<Role> roleList = roleService.selectAllRole();
        model.addAttribute("roleList", roleList);
        List<Job> jobList = jobService.selectAllJobByCompanyId(user.getCompanyId());
        model.addAttribute("jobList", jobList);
        List<String> accountNameList = userService.selectAllAccountName(user.getCompanyId());
        model.addAttribute("anl", accountNameList);
//        List<User> selectAllUserByCompanyId = userService.selectAllUserByCompanyId(user.getCompanyId());
//        model.addAttribute("allacc", selectAllUserByCompanyId);
        return "/admin/useradd";
    }
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
                                                        @RequestParam(required = false, defaultValue = "3") Integer limit,
                                                        @RequestParam(required = false, defaultValue = "", value = "searchUserName") String userName,//查询条件
                                                        @RequestParam(required = false, defaultValue = "", value = "searchUserPhone") Integer phone,
                                                        @RequestParam(required = false, defaultValue = "", value = "searchdepartmentId") Integer departmentId,
                                                        @RequestParam(required = false, defaultValue = "", value = "searchroleId") Integer roleId,
                                                        @RequestParam(required = false, defaultValue = "", value = "searchIsStatus") Integer isStatus,
                                                        @RequestParam(required = false, defaultValue = "", value = "searchWorkId") String workId,
                                                        HttpSession session, Model model) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        List<User> pageUser = userService.selectByCompanyId(user.getCompanyId(), page, limit, userName, phone, departmentId, roleId, isStatus, workId);//每页显示的数据
        //获取总数据数量
        List<User> allUser = userService.selectAllUserCount(user.getCompanyId(), userName, phone, departmentId, roleId, isStatus, workId);
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

    /*
     * @Description TODO
     * 查询所选需要编辑的人员
     * @Author
     * @Date 2021-02-22
     * @params
     * @Return
     **/
    @GetMapping("/touseredit{userId}")
    public String touseredit(Model model, @PathVariable("userId") Integer userId, HttpSession session) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        List<Department> departmentList = departmentService.selectDepartmentByCompanyId2(user.getCompanyId());
        model.addAttribute("departmentList", departmentList);
        List<Role> roleList = roleService.selectAllRole();
        model.addAttribute("roleList", roleList);
        List<Job> jobList = jobService.selectAllJobByCompanyId(user.getCompanyId());
        model.addAttribute("jobList", jobList);
        User selectUserByUserId = userService.selectUserByUserId(userId);
        model.addAttribute("user", selectUserByUserId);
        model.addAttribute("userDid", selectUserByUserId.getDepartmentId() != null ? selectUserByUserId.getDepartmentId() : 0);//防止部门为空的bug
        model.addAttribute("userJid", selectUserByUserId.getJobId() != null ? selectUserByUserId.getJobId() : 0);//防止岗位为空的bug

        String departmentName = departmentService.selectDName(selectUserByUserId.getDepartmentId());
        model.addAttribute("dName", departmentName);
        String jobName = jobService.selectJName(selectUserByUserId.getJobId());
        model.addAttribute("jName", jobName);
        String roleName = roleService.selectRName(selectUserByUserId.getRoleId());
        model.addAttribute("rName", roleName);
        return "/admin/useredit";
    }

    /*
     * @Description TODO
     * 添加人员功能
     * @Author
     * @Date 2021-02-22
     * @params
     * @Return
     **/
    @PostMapping("/addUser")
    @ResponseBody
    public void addUser(User user, HttpSession session) throws Exception {
//        System.out.println(user);
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user1 = userService.selectUserByUserId(loginUser.getUserId());
        user.setCompanyId(user1.getCompanyId());
        List<User> userList = userService.selectAllUserX(user1.getCompanyId());
        for (User user2 : userList) {
            if ((user2.getAccountName()).equals(user.getAccountName())) {
                throw new Exception("账号重复，请重新填写！");
            }
        }
        userService.addUser(user);
//        return "redirect:/touseradd";
    }

    /*
     * @Description TODO
     * 修改人员信息
     * @Author
     * @Date 2021-02-23
     * @params
     * @Return
     **/
    @PostMapping("/updateUser")
    @ResponseBody
    public String updateUser(User user, HttpSession session) {

        userService.updateUser(user);
        return null;
    }

    /*
     * @Description TODO
     * 获取当前登录用户信息
     * @Author
     * @Date 2021-02-25
     * @params
     * @Return
     **/
    @GetMapping("/selectMyAccount")
    public String selectMyAccount(Model model, HttpSession session) {

        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());

        String departmentName = departmentService.selectDName(loginUser.getDepartmentId());
        model.addAttribute("dName", departmentName);
        String jobName = jobService.selectJName(loginUser.getJobId());
        model.addAttribute("jName", jobName);
        String roleName = roleService.selectRName(loginUser.getRoleId());
        model.addAttribute("rName", roleName);
        model.addAttribute("user", user);
        model.addAttribute("loginUser", loginUser);


        return "user/myaccount";
    }

    /*
     * @Description TODO
     * 修改自己的信息
     * @Author
     * @Date 2021-02-25
     * @params
     * @Return
     **/
    @PostMapping("/updateMyaccount")
    public String updateMyaccount(User user, HttpSession session) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        user.setUserId(loginUser.getUserId());
        user.setCompanyId(loginUser.getCompanyId());
        user.setUserOnline(loginUser.getUserOnline());
        user.setIsStatus(loginUser.getIsStatus());
        userService.updateUser(user);
        return "redirect:/selectMyAccount";
    }

    /*
     * @Description TODO
     * 删除功能
     * @Author
     * @Date 2021-05-05
     * @params
     * @Return
     **/
    @PostMapping("/deleteUser{userId}")
    @ResponseBody
    public String deleteUser(User user, @PathVariable("userId") Integer userId) throws Exception {
        List<Fixedcard> fixedcards = fixedcardService.selectFixedcardByUserId(userId);
        List<Department> departmentList = departmentService.selectdepartByManager(userId);
        if (fixedcards.size() != 0) {
            throw new Exception("该用户已被引用！");
        } else if (departmentList.size() != 0) {
            throw new Exception("该用户已被应用！");
        } else {
            userService.deleteUser(userId);
        }
        return null;
    }
}
