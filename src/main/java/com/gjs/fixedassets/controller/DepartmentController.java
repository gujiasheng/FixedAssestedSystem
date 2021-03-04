package com.gjs.fixedassets.controller;

import com.gjs.fixedassets.entity.Department;
import com.gjs.fixedassets.entity.User;
import com.gjs.fixedassets.mapper.DepartmentMapper;
import com.gjs.fixedassets.service.DepartmentService;
import com.gjs.fixedassets.service.UserService;
import com.mysql.cj.xdevapi.JsonArray;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    public String toDepartmentList(Model model) {
        List<Department> departmentList = departmentService.selectDepartmentByCompanyId2(1);
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
                                                              @RequestParam(required = false, defaultValue = "10") Integer limit,
                                                              @RequestParam(required = false, defaultValue = "", value = "searchdepartmentName") Integer departmentId
            , HttpSession session, Model model) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;

        List<Department> departmentList = departmentService.selectDepartmentByCompanyId(1, page, limit, departmentId);
        List<Department> departmentCountList = departmentService.selectAllDepartmentCount(1, departmentId);
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
    public String toadddepartment(Model model) {

        List<Department> departmentList = departmentService.selectDepartmentByCompanyId2(1);
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
    public String ajaxGetUser(Model model, HttpServletRequest request) {
        List<User> userList = userService.selectAllUserByCompanyId(1);
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
    @PostMapping("/addDepartment")
    public String addDepartment(Department department, @RequestParam("departmentManager") Integer departmentManager) {
        department.setDepartmentManager(departmentManager);
        departmentService.addDepartment(department);
        return "redirect:/toAddDepartment";
    }


}
