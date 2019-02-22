package com.example.productservice.dao;

import com.example.productservice.domain.Adress;
import com.example.productservice.domain.OrderDetail;
import com.example.productservice.domain.OrderMaster;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by zhanglh on 2018/5/6.
 */
@Mapper
public interface OrderDAO {

    void   changeOrderStatusById(@Param("orderId") String orderId, @Param("updateTime") LocalDateTime updateTime);

}
