package com.example.productservice.Interface;

import com.example.productservice.domain.UserMain;
import com.example.productservice.domain.UserMainDetail;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Map;

/**
 * Created by zhanglh on 2018/5/28.
 */
@FeignClient(value = "user-service")
public interface Register {
    @RequestMapping(value = "/saveUserMain",method = RequestMethod.POST)
    String saveUserMain(@RequestBody UserMain userMain);

    @RequestMapping(value = "/saveUserMainDetail",method = RequestMethod.POST)
    String saveUserMainDetail(@RequestBody UserMainDetail userMainDetail);
}
