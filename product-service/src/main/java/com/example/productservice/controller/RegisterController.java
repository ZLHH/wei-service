package com.example.productservice.controller;

import com.example.productservice.Interface.Login;
import com.example.productservice.Interface.Register;
import com.example.productservice.domain.Msg;
import com.example.productservice.domain.UserMain;
import com.example.productservice.domain.UserMainDetail;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;

/**
 * Created by zhanglh on 2018/3/14.
 */
@RestController
@RequestMapping("/shopping")
public class RegisterController {

    private Logger logger = LoggerFactory.getLogger(RegisterController.class);

    @Autowired
    Register register;
    @Autowired
    Login login;

    @RequestMapping(value = "/register",method = RequestMethod.POST)
    @ResponseBody
    public Msg register(HttpServletRequest request, String account, String password, String email, String verifycode) {
        HttpSession session = request.getSession();
        UserMain userMain = new UserMain();
        userMain=login.getUserMain(account);
        if (userMain != null){
            return Msg.error("账号已存在！");
        }else{
            userMain = new UserMain();
            userMain.setName(account.toString());
            userMain.setEmail(email.toString());
            userMain.setNickName("user");
            userMain.setStatus(0);
            userMain.setCreateTime(LocalDateTime.now());
            userMain.setRole(0);
            String result=register.saveUserMain(userMain);
            if (result.equals("success"))
                logger.info("保存userMain成功");
            session.setAttribute("userMain", userMain);
        }
        userMain=login.getUserMain(account);
        UserMainDetail userMainDetail = new UserMainDetail();
        userMainDetail.setPassword(password);
        userMainDetail.setPhoneNumber("15279106323");
        userMainDetail.setCreateTime(LocalDateTime.now());
        userMainDetail.setUserId(userMain.getId());
        String resulta=register.saveUserMainDetail(userMainDetail);
        if (resulta.equals("success"))
            logger.info("保存userMain成功");
        return Msg.success("注册成功!");

    }
}
