package com.example.userservice.controller;

import com.example.userservice.domain.Msg;
import com.example.userservice.domain.UserMain;
import com.example.userservice.domain.UserMainDetail;
import com.example.userservice.service.LoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * Created by zhanglh on 2018/3/12.
 */
@RestController
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    LoginService loginService;


    @RequestMapping("/userMain")
    public UserMain getUserMain(@RequestParam String account) {
        logger.info("获取UserMain成功");
        return loginService.querryIdByName(account);
    }

    @RequestMapping(value = "/userMainDetail")
    public UserMainDetail getUserMainDetail(@RequestParam Integer id){
        logger.info("获取userMainDetail成功");
        return  loginService.querryById(id);

    }
}
