package com.example.userservice.service;

import com.example.userservice.dao.RegisterDAO;
import com.example.userservice.domain.UserMain;
import com.example.userservice.domain.UserMainDetail;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhanglh on 2018/3/14.
 */
@Service
public class RegisterService {

    @Autowired
    RegisterDAO registerDAO;

    public void register(UserMain userMain){
        registerDAO.save(userMain);
    }

    public void registerDetail(UserMainDetail userMainDetail){
        registerDAO.saveDetail(userMainDetail);
    }
}
