package com.example.productservice.service;

import com.example.productservice.dao.ProductDAO;
import com.example.productservice.domain.ProductInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhanglh on 2018/5/17.
 */
@Service
public class ProductService {

    @Autowired
    ProductDAO productDAO;

    public List<ProductInfo> querryAll(Integer category){
        return productDAO.querryAll(category);
    }
}
