package com.example.productservice.Interface;

import com.example.productservice.domain.UserMain;
import com.example.productservice.domain.UserMainDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by zhanglh on 2018/5/28.
 */
@FeignClient(value = "user-service")
public interface Login {
    @RequestMapping(value = "/userMain",method = RequestMethod.GET)
    UserMain getUserMain(@RequestParam(value = "account") String account);

    @RequestMapping(value = "/userMainDetail",method = RequestMethod.GET)
    UserMainDetail getUserMainDetail(@RequestParam(value = "id") Integer id);
}
