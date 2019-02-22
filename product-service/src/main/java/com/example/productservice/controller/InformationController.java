package com.example.productservice.controller;

import com.example.productservice.domain.Information;
import com.example.productservice.domain.Msg;
import com.example.productservice.service.InformationService;
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
@RequestMapping("/information")
public class InformationController {

    @Autowired
    InformationService informationService;

    @RequestMapping(value = "/list", method = RequestMethod.GET)
    @ResponseBody
    public Msg showProduct(@RequestParam(value="pageNum",defaultValue="1")Integer pageNum){
        List<Information> list = new ArrayList<Information>();
        PageHelper.startPage(pageNum, 6);
        list=informationService.querryAll();
        if(!list.isEmpty()&&list.size()!=0){
            PageInfo<Information> pageInfo=new PageInfo<>(list);
            return Msg.success("").add("pageInfo", pageInfo);
        }
        return Msg.error("");
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Msg getProductById(@PathVariable Integer id) {
        Information information=informationService.getInformationById(id);
        if (information!=null){
            return Msg.success("").add("information", information);
        }
        return Msg.error("");
    }
}
