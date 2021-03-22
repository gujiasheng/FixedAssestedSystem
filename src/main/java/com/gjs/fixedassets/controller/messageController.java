package com.gjs.fixedassets.controller;

import com.gjs.fixedassets.entity.Mymessage;
import com.gjs.fixedassets.entity.User;
import com.gjs.fixedassets.service.MyMessageService;
import com.gjs.fixedassets.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Slf4j
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
    public String toMain(Model model, HttpSession session) throws ParseException {
        Object object = session.getAttribute("user");
        User loginUser = (User) object;
        User user = userService.selectUserByUserId(loginUser.getUserId());
        List<Mymessage> mymessageList = myMessageService.selectMessageLimit(user.getUserId());
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        for (Mymessage m : mymessageList) {

        }

        model.addAttribute("mml", mymessageList);

        return "common/main";
    }
}
