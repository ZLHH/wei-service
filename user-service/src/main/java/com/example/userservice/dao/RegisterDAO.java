package com.example.userservice.dao;

import com.example.userservice.domain.UserMain;
import com.example.userservice.domain.UserMainDetail;
import org.apache.ibatis.annotations.Mapper;

/**
 * Created by zhanglh on 2018/3/14.
 */
@Mapper
public interface RegisterDAO {

    void save(UserMain userMain);

    void saveDetail(UserMainDetail userMainDetail);
}
