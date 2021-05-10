package com.gjs.fixedassets.controller;

import com.gjs.fixedassets.entity.Department;
import com.gjs.fixedassets.entity.Job;
import com.gjs.fixedassets.entity.User;
import com.gjs.fixedassets.service.DepartmentService;
import com.gjs.fixedassets.service.JobService;
import com.gjs.fixedassets.service.UserService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private UserService userService;
    @Autowired
    private DepartmentService departmentService;

    /*
     * @Description TODO
     * 前往岗位列表页面
     * @Author
     * @Date 2021-04-06
     * @params
     * @Return
     **/
    @GetMapping("/toJobList")
    public String toJobList() {
        return "job/joblist";
    }

    /*
     * @Description TODO
     * 前往岗位添加页面
     * @Author
     * @Date 2021-04-06
     * @params
     * @Return
     **/
    @GetMapping("/toJobAdd")
    public String toJobAdd(Model model, HttpSession session) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        List<Department> departmentList = departmentService.selectDepartmentByCompanyId2(user.getCompanyId());
        model.addAttribute("dl", departmentList);

        return "job/jobadd";
    }

    /*
     * @Description TODO
     * 岗位添加
     * @Author
     * @Date 2021-04-07
     * @params
     * @Return
     **/
    @PostMapping("/addJob")
    @ResponseBody
    public void addJob(Job job, HttpSession session, @RequestParam(value = "jobName") String jobName, @RequestParam(required = false, defaultValue = "", value = "departmentId") Integer departmentId) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        job.setCompanyId(user.getCompanyId());
        job.setJobName(jobName);
        job.setDepartmentId(departmentId);
        jobService.addJob(job);
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
    @GetMapping("/selectAllJobByCompanyId")
    public Map<String, Object> selectAllJobByCompanyId(@RequestParam(required = false, defaultValue = "1") Integer page,
                                                       @RequestParam(required = false, defaultValue = "5") Integer limit,
                                                       @RequestParam(required = false, defaultValue = "", value = "searchJobName") String jobName,//查询条件
                                                       HttpSession session, Model model) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        List<Job> jobList = jobService.selectJobByCompanyId(user.getCompanyId(), page, limit, jobName);
        //获取总数据数量
        List<Job> jobCount = jobService.selectJobCount(user.getCompanyId(), jobName);
        //用layui的table渲染数据的json有格式要求，需要封装一下
        Map<String, Object> map = new HashMap<>();
        map.put("code", 0);
        map.put("msg", "操作成功");
        map.put("count", jobCount.size());
        map.put("data", jobList);
        return map;
    }

    @GetMapping("toJobedit{jobId}")
    public String editDepartment(Model model, @PathVariable("jobId") Integer jobId, HttpSession session) {
        Object obj = session.getAttribute("user");
        User loginUser = (User) obj;

        Job job = jobService.selectJobByPk(jobId);
        List<User> userList = userService.selectAllUserByCompanyId(loginUser.getCompanyId());
        List<Department> departmentList = departmentService.selectDepartmentByCompanyId2(loginUser.getCompanyId());
        model.addAttribute("dl", departmentList);
        model.addAttribute("job", job);
        model.addAttribute("ul", userList);
        return "job/jobedit";
    }

    @GetMapping("/ajaxGetDept")
    @ResponseBody
    public String ajaxGetUser(Model model, HttpSession session) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        List<Department> departmentList = departmentService.selectDepartmentByCompanyId2(user.getCompanyId());
        model.addAttribute("userList", departmentList);
        JSONArray jsonArray = JSONArray.fromObject(departmentList);
        String userJson = jsonArray.toString();

        return userJson;

    }

    @PostMapping("/deleteJob{jobId}")
    @ResponseBody
    public String deleteDepartment(Model model, @PathVariable("jobId") Integer jobId) {
        jobService.deleteJob(jobId);
        return null;
    }

    @PostMapping("/editJob")
    @ResponseBody
    public String editDepartment(Job job) {
        jobService.updateJob(job);
        return null;
    }
}
