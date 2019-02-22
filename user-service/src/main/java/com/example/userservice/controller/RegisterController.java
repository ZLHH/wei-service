package com.example.userservice.controller;

import com.example.userservice.domain.UserMain;
import com.example.userservice.domain.UserMainDetail;
import com.example.userservice.service.RegisterService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


/**
 * Created by zhanglh on 2018/3/14.
 */
@RestController
public class RegisterController {

    private Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    RegisterService registerService;

    @RequestMapping(value = "/saveUserMain",method = RequestMethod.POST)
    @ResponseBody
    public String saveUserMain(@RequestBody UserMain userMain) {
        registerService.register(userMain);
        logger.info("保存UserMain成功");
        return "success";
    }

    @RequestMapping(value = "/saveUserMainDetail",method = RequestMethod.POST)
    @ResponseBody
    public String saveUserMainDetail(@RequestBody UserMainDetail userMainDetail) {
        registerService.registerDetail(userMainDetail);
        logger.info("保存userMainDetail成功");
        return "success";
    }
}
