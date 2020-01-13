package com.sdjzu.sell.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;

/**
 * @author lychee
 * @date 2020/1/11 15:37
 */
@Controller
@RequestMapping("/pay")
public class PayController {
//    @Resource
//    private

    @GetMapping("create")
    public void  create(@RequestParam("orderId") String order,
                        @RequestParam("returnUrl") String returnUrl){
        //1.查询订单

    }

}
