package com.example.productservice.dao;

import com.example.productservice.domain.ShoppingCar;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * Created by zhanglh on 2018/4/22.
 */
@Mapper
public interface ShoppingCarDAO {

    void save(ShoppingCar shoppingCar);

    List<ShoppingCar> querryAll(Integer id);

    void delete(Integer id);

    int queryTotal(Integer id);

    ShoppingCar querryAllByIds(@Param("ids") Integer ids, @Param("id") Integer id);

    void changeStatus(Integer id);
}
