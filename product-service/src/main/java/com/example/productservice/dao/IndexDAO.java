package com.example.productservice.dao;

import com.example.productservice.domain.ProductInfo;
import com.example.productservice.domain.UserMain;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by zhanglh on 2018/3/29.
 */
@Mapper
public interface IndexDAO {

    List<ProductInfo> querryAll();

    ProductInfo getProductById(String id);

    List<UserMain> getAllUser();

}
