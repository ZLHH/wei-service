package com.example.productservice.controller;

import com.example.productservice.Interface.Order;
import com.example.productservice.domain.*;
import com.example.productservice.service.ShoppingCarService;
import com.example.productservice.utils.KeyUtil;
import com.github.pagehelper.PageInfo;
import net.sf.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhanglh on 2018/5/6.
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    private Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    Order order;

    @Autowired
    ShoppingCarService shoppingCarService;

    @RequestMapping(value = "/saveAdress",method = RequestMethod.POST)
    @ResponseBody
    public Msg register(HttpSession session, String name, String phone, String adress) {
        UserMain userMain=(UserMain) session.getAttribute("userMain");
        Adress myAdress=new Adress();
        if (userMain == null){
            return Msg.error("请先登录!");
        }else {
            myAdress.setName(name);
            myAdress.setPhone(phone);
            myAdress.setAdress(adress);
            myAdress.setUserId(userMain.getId());
            myAdress.setCreateTime(LocalDateTime.now());
            String result=order.saveAdress(myAdress);
            if (result.equals("success"))
                logger.info("保存Adress成功");
        }
        return Msg.success("提交成功!");
    }

    @RequestMapping(value = "/adresslist", method = RequestMethod.GET)
    @ResponseBody
    public Msg showAdress(HttpSession session){
        UserMain userMain=(UserMain) session.getAttribute("userMain");
        if (userMain!=null){
            List<Adress> list = order.showAdress(userMain.getId());
            if(!list.isEmpty()&&list.size()!=0){
                PageInfo<Adress> pageInfo=new PageInfo<>(list);
                return Msg.success("").add("pageInfo", pageInfo);
            }else
                return null;
        }else {
            return Msg.error("请先登录");
        }
    }

    @RequestMapping(value = "/saveOrder",method = RequestMethod.POST)
    @ResponseBody
    public Msg saveOrder(HttpSession session, String name, String phone, String adress, String ids) {
        UserMain userMain=(UserMain) session.getAttribute("userMain");
        String[] is= ids.split(",");
        String result = null;
        if (userMain == null){
            return Msg.error("请先登录!");
        }else {
            String orderId = KeyUtil.genUniqueKey();
            session.setAttribute("orderId", orderId);
            OrderDetail orderDetail = new OrderDetail();
            BigDecimal orderAmount = new BigDecimal(BigInteger.ZERO);
            OrderMaster orderMaster = new OrderMaster();
            orderMaster.setOrderId(orderId);
            orderMaster.setBuyerName(name);
            orderMaster.setBuyerAddress(adress);
            orderMaster.setBuyerPhone(phone);
            orderMaster.setOrderStatus(0);
            orderMaster.setPayStatus(0);
            orderMaster.setCreateTime(LocalDateTime.now());
            JSONArray conditionList = JSONArray.fromObject(ids);
            for (int i=0;i<conditionList.size();i++){
                ShoppingCar shoppingCar = shoppingCarService.querryAllByIds(Integer.valueOf(conditionList.get(i).toString()),userMain.getId());
                //2. 计算订单总价
                orderAmount = shoppingCar.getProductPrice()
                        .multiply(new BigDecimal(shoppingCar.getCounts()))
                        .add(orderAmount);
                //减库存
                Map<String,Object> parm =new HashMap<>();
                parm.put("counts",shoppingCar.getCounts());
                parm.put("id",shoppingCar.getProductId());
                result = order.reduce(parm);
                if (result.equals("success"))
                    logger.info("减库存成功");
                //改变购物车商品状态
                shoppingCarService.changeStatus(shoppingCar.getId());
                orderDetail.setOrderId(orderId);
                orderDetail.setProductId(shoppingCar.getProductId().toString());
                orderDetail.setProductName(shoppingCar.getProductName());
                orderDetail.setProductPrice(shoppingCar.getProductPrice());
                orderDetail.setProductQuantity(shoppingCar.getCounts());
                orderDetail.setCreateTime(LocalDateTime.now());
                orderDetail.setDetailId(KeyUtil.genUniqueKey());
                result=order.saveOrderDetail(orderDetail);
                if (result.equals("success"))
                    logger.info("保存OrderDetail成功");
            }
            orderMaster.setOrderAmount(orderAmount);
            orderMaster.setBuyerOpenid("abc123");
            result = order.saveOrderMaster(orderMaster);
            if (result.equals("success"))
                logger.info("保存OrderMaster成功");
        }
        return Msg.success("提交成功!");
    }
}
