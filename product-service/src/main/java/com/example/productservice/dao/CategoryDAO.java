package com.example.productservice.dao;

import com.example.productservice.domain.Category;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by zhanglh on 2018/5/17.
 */
@Mapper
public interface CategoryDAO {

    List<Category> querryAll();

}
