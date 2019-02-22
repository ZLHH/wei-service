package com.example.productservice.service;

import com.example.productservice.dao.TouchDAO;
import com.example.productservice.domain.Cooperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by zhanglh on 2018/5/6.
 */
@Service
public class TouchServce {

    @Autowired
    TouchDAO touchDAO;

    public void save(Cooperation cooperation){
        touchDAO.save(cooperation);
    }
}
