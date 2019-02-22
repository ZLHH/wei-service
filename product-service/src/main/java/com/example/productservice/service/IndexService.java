package com.example.productservice.service;

import com.example.productservice.dao.IndexDAO;
import com.example.productservice.dao.ShoppingCarDAO;
import com.example.productservice.domain.ProductInfo;
import com.example.productservice.domain.ShoppingCar;
import com.example.productservice.domain.UserMain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhanglh on 2018/3/29.
 */
@Service
public class IndexService {
    @Autowired
    IndexDAO indexDAO;

    @Autowired
    ShoppingCarDAO shoppingCarDAO;

    public List<ProductInfo> querryAll(){
        return indexDAO.querryAll();
    }

    public ProductInfo getProductById(String id){
        return indexDAO.getProductById(id);
    }

    public List<UserMain> getAllUser(){
        return indexDAO.getAllUser();
    }

    public List<ShoppingCar> getAllShoppingCarById(Integer id){
        return shoppingCarDAO.querryAll(id);
    }
}
