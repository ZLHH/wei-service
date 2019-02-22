package com.example.productservice.dao;

import com.example.productservice.domain.ProductInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhanglh on 2018/5/17.
 */
@Mapper
public interface ProductDAO {

    List<ProductInfo> querryAll(@Param("category") Integer category);

}
