package com.sdjzu.sell.service.Impl;

import com.sdjzu.sell.dto.OrderDTO;
import com.sdjzu.sell.enums.ResultEnum;
import com.sdjzu.sell.exception.SellException;
import com.sdjzu.sell.service.BuyerService;
import com.sdjzu.sell.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author lychee
 * @date 2020/1/15 20:40
 */
@Service
@Slf4j
public class BuyerServiceImpl implements BuyerService {
    @Resource
    private OrderService orderService;

    @Override
    public OrderDTO findOrderOne(String openid, String orderId) {
        return checkOrderOwner(openid, orderId);
    }

    @Override
    public OrderDTO cancelOrder(String openid, String orderId) {
        OrderDTO orderDTO = checkOrderOwner(openid, orderId);
        if (Objects.isNull(orderDTO)) {
            log.error("【取消订单】查不到该订单, orderId={}", orderId);
            throw new SellException(ResultEnum.ORDER_NOT_EXIST);
        }

        return orderService.cancel(orderDTO);
    }

    private OrderDTO checkOrderOwner(String openid, String orderId) {
        OrderDTO orderDTO = orderService.findById(orderId);
        if (Objects.isNull(orderDTO)) {
            return null;
        }
        //判断是否是自己的订单
        if (!orderDTO.getBuyerOpenid().equalsIgnoreCase(openid)) {
            log.error("【查询订单】订单的openid不一致. openid={}, orderDTO={}", openid, orderDTO);
            throw new SellException(ResultEnum.ORDER_OWNER_ERROR);
        }
        return orderDTO;
    }
}
