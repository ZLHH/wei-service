package com.example.productservice.service;

import com.example.productservice.dao.OrderDAO;
import com.example.productservice.domain.Adress;
import com.example.productservice.domain.OrderDetail;
import com.example.productservice.domain.OrderMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by zhanglh on 2018/5/6.
 */
@Service
public class OrderService {

    @Autowired
    OrderDAO orderDAO;

    public void changeOrderStatusById(String orderId, LocalDateTime updateTime){
        orderDAO.changeOrderStatusById(orderId,updateTime);
     }
}
