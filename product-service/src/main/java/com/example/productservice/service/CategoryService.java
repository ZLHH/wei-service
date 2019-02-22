package com.example.productservice.service;

import com.example.productservice.dao.CategoryDAO;
import com.example.productservice.domain.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by zhanglh on 2018/5/17.
 */
@Service
public class CategoryService {

    @Autowired
    CategoryDAO categoryDAO;

    public List<Category> querryAll(){
        return categoryDAO.querryAll();
    }

}
