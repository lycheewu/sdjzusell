package com.sdjzu.sell.service;

import com.sdjzu.sell.dto.OrderDTO;

/**
 * 买家
 * @author lychee
 * @date 2020/1/15 20:34
 */
public interface BuyerService {
    //查询一个订单
    OrderDTO findOrderOne(String openid, String orderId);

    //取消订单
    OrderDTO cancelOrder(String openid, String orderId);
}
