package com.example.userservice.dao;

import com.example.userservice.domain.UserMain;
import com.example.userservice.domain.UserMainDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by zhanglh on 2018/3/13.
 */
@Mapper
public interface LoginDAO {
    UserMain querryIdByName(String name);

    UserMainDetail querryById(Integer id);


}
