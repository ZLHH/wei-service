package com.example.orderservice.dao;

import com.example.orderservice.domain.Adress;
import com.example.orderservice.domain.OrderDetail;
import com.example.orderservice.domain.OrderMaster;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanglh on 2018/5/6.
 */
@Mapper
public interface OrderDAO {

    void saveAdress(Adress adress);

    List<Adress> showAdress(Integer userid);

    void reduce(Map<String,Object> parm);

    void saveOrderMaster(OrderMaster orderMaster);

    void saveOrderDetail(OrderDetail orderDetail);

    void   changeOrderStatusById(@Param("orderId") String orderId, @Param("updateTime") LocalDateTime updateTime);
}
