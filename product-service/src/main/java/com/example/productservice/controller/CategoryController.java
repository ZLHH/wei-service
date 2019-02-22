package com.example.productservice.controller;

import com.example.productservice.domain.Category;
import com.example.productservice.domain.Msg;
import com.example.productservice.service.CategoryService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanglh on 2018/5/17.
 */
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Msg showProduct(@RequestParam(value="pageNum",defaultValue="1")Integer pageNum){
        List<Category> list = new ArrayList<Category>();
        PageHelper.startPage(pageNum, 6);
        list=categoryService.querryAll();
        if(!list.isEmpty()&&list.size()!=0){
            PageInfo<Category> pageInfo=new PageInfo<>(list);
            return Msg.success("").add("category", pageInfo);
        }
        return Msg.error("");
    }
}
