package com.example.productservice.controller;

import com.example.productservice.domain.Msg;
import com.example.productservice.domain.ProductInfo;
import com.example.productservice.service.HotService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanglh on 2018/5/6.
 */
@RestController
@RequestMapping("/hot")
public class HotController {

    @Autowired
    HotService hotService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Msg showProduct(@RequestParam(value="pageNum",defaultValue="1")Integer pageNum, @RequestParam(value="category")Integer category){
        List<ProductInfo> list = new ArrayList<ProductInfo>();
        PageHelper.startPage(pageNum, 6);
        list=hotService.querryAll(category);
        if(!list.isEmpty()&&list.size()!=0){
            PageInfo<ProductInfo> pageInfo=new PageInfo<>(list);
            return Msg.success("").add("pageInfo", pageInfo);
        }
        return Msg.error("");
    }
}
