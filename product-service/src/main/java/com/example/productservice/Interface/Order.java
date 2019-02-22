package com.example.productservice.Interface;

import com.example.productservice.domain.Adress;
import com.example.productservice.domain.OrderDetail;
import com.example.productservice.domain.OrderMaster;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Created by zhanglh on 2018/6/1.
 */
@FeignClient(value = "order-service")
public interface Order {

    @RequestMapping(value = "/saveAdress",method = RequestMethod.POST)
    String saveAdress(@RequestBody Adress myAdress);

    @RequestMapping(value = "/saveOrderDetail",method = RequestMethod.POST)
    String saveOrderDetail(@RequestBody OrderDetail orderDetail);

    @RequestMapping(value = "/saveOrderMaster",method = RequestMethod.POST)
    String saveOrderMaster(@RequestBody OrderMaster orderMaster);

    @RequestMapping(value = "/adresslist", method = RequestMethod.GET)
    List<Adress> showAdress(@RequestParam(value = "userid") Integer userid);

    @RequestMapping(value = "/reduce",method = RequestMethod.POST)
    String reduce(@RequestBody Map<String,Object> parm);

}
