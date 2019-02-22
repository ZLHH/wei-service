package com.example.userservice.service;



import com.example.userservice.dao.LoginDAO;
import com.example.userservice.domain.UserMain;
import com.example.userservice.domain.UserMainDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhanglh on 2018/3/12.
 */
@Service
public class LoginService {

    @Autowired
    LoginDAO loginDAO;

    public UserMain querryIdByName(String name){
        return loginDAO.querryIdByName(name);
    }

    public UserMainDetail querryById(Integer id){
        return loginDAO.querryById(id);
    }
}
