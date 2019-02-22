package com.example.productservice.dao;

import com.example.productservice.domain.ProductInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhanglh on 2018/5/6.
 */
@Mapper
public interface HotDAO {

    List<ProductInfo> querryAll(@Param("category") Integer category);
}
