package com.example.orderservice.controller;

//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONArray;
import com.example.orderservice.domain.Adress;
import com.example.orderservice.domain.OrderDetail;
import com.example.orderservice.domain.OrderMaster;
import com.example.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanglh on 2018/5/6.
 */
@RestController
public class OrderController {

    @Autowired
    OrderService orderService;

    @RequestMapping(value = "/saveAdress",method = RequestMethod.POST)
    @ResponseBody
    public String saveAdress(@RequestBody Adress myAdress) {
        orderService.saveAdress(myAdress);
        return "success!";
    }

    @RequestMapping(value = "/saveOrderDetail",method = RequestMethod.POST)
    @ResponseBody
    public String saveOrderDetail(@RequestBody OrderDetail orderDetail) {
        orderService.saveOrderDetail(orderDetail);
        return "success!";
    }

    @RequestMapping(value = "/saveOrderMaster",method = RequestMethod.POST)
    @ResponseBody
    public String saveOrderMaster(@RequestBody OrderMaster orderMaster) {
        orderService.saveOrderMaster(orderMaster);
        return "success!";
    }

    @RequestMapping(value = "/adresslist", method = RequestMethod.GET)
    @ResponseBody
    public List<Adress> showAdress(@RequestParam Integer userid){
        List<Adress> list = orderService.showAdress(userid);
        return list;
    }

    @RequestMapping(value = "/reduce",method = RequestMethod.POST)
    @ResponseBody
    public String reduce(@RequestBody Map<String,Object> parm) {
        orderService.reduce(parm);
        return "success";
    }
}
