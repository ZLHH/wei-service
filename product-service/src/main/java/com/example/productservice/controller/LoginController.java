package com.example.productservice.controller;

import com.example.productservice.Interface.Login;
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

/**
 * Created by zhanglh on 2018/3/12.
 */
@RestController
@RequestMapping("/shopping")
public class LoginController {

    private Logger logger = LoggerFactory.getLogger(LoginController.class);

    @Autowired
    Login login;


    @RequestMapping(value = "/checklogin", method = RequestMethod.POST)
    @ResponseBody
    public Msg checklogin(HttpSession session){
        UserMain userMain=(UserMain) session.getAttribute("userMain");
        if(userMain!=null){
            return Msg.success("").add("student", userMain);
        }
        return Msg.error("");
    }


    @RequestMapping("/login")
    @ResponseBody
    public Msg login(HttpServletRequest request, String account, String password) {
        HttpSession session = request.getSession();
        UserMain userMain=login.getUserMain(account);
        if (userMain!=null){
            UserMainDetail userMainDetail = login.getUserMainDetail(userMain.getId());
            if (userMainDetail.getPassword().equals(password)){
                session.setAttribute("userMain", userMain);
                return Msg.success("登陆成功");
            }else {
                return Msg.error("登陆失败，密码错误");
            }
        }else {
            return Msg.error("登陆失败，账号不存在");
        }
    }

    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    @ResponseBody
    public void logout(HttpSession session){
        session.removeAttribute("userMain");
    }
}
