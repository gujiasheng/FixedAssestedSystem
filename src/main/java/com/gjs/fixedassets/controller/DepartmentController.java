package com.gjs.fixedassets.controller;

import com.gjs.fixedassets.entity.Department;
import com.gjs.fixedassets.entity.User;
import com.gjs.fixedassets.service.DepartmentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
@Controller
public class DepartmentController {
    @Autowired
    private DepartmentService departmentService;

    @GetMapping("/todepartmentlist")
    public String toDepartmentList(Model model) {
        List<Department> departmentList = departmentService.selectDepartmentByCompanyId2(1);
        model.addAttribute("departmentList", departmentList);
        return "department/departmentlist";
    }

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
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "操作成功");
        map.put("count", departmentCount);
        map.put("data", departmentList);
//        System.out.println(departmentList);
        return map;
    }

}
