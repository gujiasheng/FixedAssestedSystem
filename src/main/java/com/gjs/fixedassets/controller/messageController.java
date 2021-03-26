package com.gjs.fixedassets.controller;

import com.gjs.fixedassets.entity.Mymessage;
import com.gjs.fixedassets.entity.User;
import com.gjs.fixedassets.service.MyMessageService;
import com.gjs.fixedassets.service.UserService;
import com.mysql.cj.xdevapi.JsonArray;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
public class messageController {

    @Autowired
    private MyMessageService myMessageService;

    @Autowired
    private UserService userService;

    /*
     * @Description TODO
     * 主页
     * @Author
     * @Date 2021-03-22
     * @params
     * @Return
     **/
    @GetMapping("/tomain")
    public String toMain(Model model, HttpSession session
    ) throws ParseException {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        List<Mymessage> mymessageList = myMessageService.selectMessageLimit(user.getUserId());

        model.addAttribute("mml", mymessageList);
        List<Mymessage> mymessageList1 = myMessageService.selectIsNewCount(user.getUserId());
        model.addAttribute("newMess", mymessageList1);
        return "common/main";
    }

    @GetMapping("/tomymessage")
    public String toMyMessage(Model model, HttpSession session,
                              @RequestParam(value = "page", required = false, defaultValue = "1") Integer page,
                              @RequestParam(value = "limit", required = false, defaultValue = "8") Integer limit) {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        List<Mymessage> myMessageCount = myMessageService.selectMessageCount(user.getUserId());
        int count = myMessageCount.size();
        model.addAttribute("count", count);
        List<Mymessage> myMessagePage = myMessageService.selectMessagePage(user.getUserId(), page, limit);
        model.addAttribute("mml", myMessagePage);

        return "common/mymessage";
    }

    /*
     * @Description TODO
     *  通过ajax调用消息查询
     * @Author
     * @Date 2021-03-23
     * @params
     * @Return
     **/
    @GetMapping("/ajaxGetMess")
    @ResponseBody
    public String ajaxGetMess(Model model, HttpSession session, @RequestParam(value = "page") Integer page, @RequestParam(value = "limit") Integer limit) {
        Object obj = session.getAttribute("user");
        User loginUser = (User) obj;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        List<Mymessage> mymessageList = myMessageService.selectMessagePage(user.getUserId(), page, limit);
        JSONArray jsonArray = JSONArray.fromObject(mymessageList);
        String messJson = jsonArray.toString();
        return messJson;
    }

    /*
     * @Description TODO
     * 点击消息isNew+1
     * @Author
     * @Date 2021-03-26
     * @params
     * @Return
     **/
    @PostMapping("/ajaxAddIsNew")
    @ResponseBody
    public void ajaxAddIsNew(@RequestParam("messId") Integer messId) {
        myMessageService.updateIsNew(messId, 2);

    }
}
