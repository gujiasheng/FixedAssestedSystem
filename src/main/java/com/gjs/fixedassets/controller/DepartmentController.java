package com.gjs.fixedassets.controller;

import com.gjs.fixedassets.entity.Department;
import com.gjs.fixedassets.entity.FixedType;
import com.gjs.fixedassets.entity.User;
import com.gjs.fixedassets.mapper.DepartmentMapper;
import com.gjs.fixedassets.mapper.UserMapper;
import com.gjs.fixedassets.service.DepartmentService;
import com.gjs.fixedassets.service.UserService;
import com.mysql.cj.xdevapi.JsonArray;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.apache.ibatis.annotations.Mapper;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;
    @Autowired
    private DepartmentMapper departmentMapper;
    @Autowired
    private UserService userService;

    /*
     * @Description TODO
     * 打开部门列表页面
     * @Author
     * @Date 2021-03-03
     * @params
     * @Return
     **/
    @GetMapping("/todepartmentlist")
    public String toDepartmentList(Model model, HttpSession session) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());

        List<Department> departmentList = departmentService.selectDepartmentByCompanyId2(user.getCompanyId());
        model.addAttribute("departmentList", departmentList);
        return "department/departmentlist";
    }

    /*
     * @Description TODO
     * 获取部门列表数据
     * @Author
     * @Date 2021-03-03
     * @params
     * @Return
     **/
    @GetMapping("/selectAllDepartmentByCompanyId")
    @ResponseBody
    public Map<String, Object> selectAllDepartmentByCompanyId(@RequestParam(required = false, defaultValue = "1") Integer page,
                                                              @RequestParam(required = false, defaultValue = "5") Integer limit,
                                                              @RequestParam(required = false, defaultValue = "", value = "searchdepartmentName") Integer departmentId
            , HttpSession session, Model model) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        List<Department> departmentList = departmentService.selectDepartmentByCompanyId(user.getCompanyId(), page, limit, departmentId);
        List<Department> departmentCountList = departmentService.selectAllDepartmentCount(user.getCompanyId(), departmentId);
        int departmentCount = departmentCountList.size();
//        for (Department de :
//                departmentList) {
//            System.out.println(de);
//        }
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "操作成功");
        map.put("count", departmentCount);
        map.put("data", departmentList);
//        System.out.println(departmentList);
        return map;
    }

    /*
     * @Description TODO
     * 打开部门添加页面
     * @Author
     * @Date 2021-03-03
     * @params
     * @Return
     **/
    @GetMapping("/toAddDepartment")
    public String toadddepartment(Model model, HttpSession session) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());

        List<Department> departmentList = departmentService.selectDepartmentByCompanyId2(user.getCompanyId());
        model.addAttribute("departmentList", departmentList);

        return "department/departmentadd";
    }

    /*
     * @Description TODO
     * lay-search无法用thymeleaf的方式动态取值，改用ajax
     * @Author
     * @Date 2021-03-03
     * @params
     * @Return
     **/
    @GetMapping("/ajaxGetUser")
    @ResponseBody
    public String ajaxGetUser(Model model, HttpSession session) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        List<User> userList = userService.selectAllUserByCompanyId(user.getCompanyId());
        model.addAttribute("userList", userList);
        JSONArray jsonArray = JSONArray.fromObject(userList);
        String userJson = jsonArray.toString();

        return userJson;

    }

    /*
     * @Description TODO
     * 部门添加
     * @Author
     * @Date 2021-03-03
     * @params
     * @Return
     **/
    @RequestMapping("/adddepartment")
    @ResponseBody
    public void addDepartment(Department department, @RequestParam("departmentManager") Integer departmentManager, HttpSession session) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        department.setCompanyId(user.getCompanyId());
        department.setDepartmentManager(departmentManager);
        departmentService.addDepartment(department);
    }

    /*
     * @Description TODO
     *  前往部门修改页面
     * @Author
     * @Date 2021-03-04
     * @params
     * @Return
     **/
    @GetMapping("toDepartmentedit{departmentId}")
    public String editDepartment(Model model, @PathVariable("departmentId") Integer departmentId, HttpSession session) {
        Object obj = session.getAttribute("user");
        User loginUser = (User) obj;

        Department department = departmentService.selectByPrimaryKey(departmentId);
        List<User> userList = userService.selectAllUserByCompanyId(loginUser.getCompanyId());
        User manager = userService.selectUserByUserId(department.getDepartmentManager());
        department.setDepartmentManagerObj(manager);
        model.addAttribute("department", department);
        model.addAttribute("ul", userList);
        return "department/departmentedit";
    }

    /*
     * @Description TODO
     * 部门删除
     * @Author
     * @Date 2021-03-04
     * @params
     * @Return
     **/
    @PostMapping("/deleteDepartment{departmentId}")
    @ResponseBody
    public String deleteDepartment(Model model, @PathVariable("departmentId") Integer departmentId) throws Exception {
        List<User> userList = userService.selectUserByDepartmentId(departmentId);
        if (userList.size() != 0) {
            throw new Exception("该部门已被引用！");
        } else {
            departmentService.deleteDepartment(departmentId);
        }

        return null;
    }

    /*
     * @Description TODO
     * 部门修改
     * @Author
     * @Date 2021-03-04
     * @params
     * @Return
     **/
    @PostMapping("/editDepartment")
    @ResponseBody
    public String editDepartment(Model model, HttpSession session, Department department, @RequestParam(required = false, defaultValue = "", value = "oldManager") Integer oldManager) {
        departmentService.updateDepartment(department);
        if (department.getDepartmentManager() != oldManager) {
            User user = userService.selectUserByUserId(department.getDepartmentManager());
            user.setDepartmentId(department.getDepartmentId());
            userService.updateUser(user);
        }
        return null;
    }
}
