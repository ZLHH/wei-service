package com.example.productservice.dao;

import com.example.productservice.domain.Information;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * Created by zhanglh on 2018/5/6.
 */
@Mapper
public interface InformationDAO {

    List<Information> querryAll();

    Information getInformationById(Integer id);

}
