package com.example.orderservice.service;

import com.example.orderservice.dao.OrderDAO;
import com.example.orderservice.domain.Adress;
import com.example.orderservice.domain.OrderDetail;
import com.example.orderservice.domain.OrderMaster;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanglh on 2018/5/6.
 */
@Service
public class OrderService {

    @Autowired
    OrderDAO orderDAO;

    public void saveAdress(Adress adress){
        orderDAO.saveAdress(adress);
    }

    public List<Adress> showAdress(Integer userid){
        List<Adress> adressList=orderDAO.showAdress(userid);
        return adressList;
    }

    public void reduce(Map<String,Object> parm){
        orderDAO.reduce(parm);
    }

    public void saveOrderMaster(OrderMaster orderMaster){
        orderDAO.saveOrderMaster(orderMaster);
    }

    public void saveOrderDetail(OrderDetail orderDetail){
        orderDAO.saveOrderDetail(orderDetail);
    }

    public void queryOrderById(String orderId, LocalDateTime updateTime){
        orderDAO.changeOrderStatusById(orderId,updateTime);
     }
}
