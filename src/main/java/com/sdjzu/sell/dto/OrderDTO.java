package com.sdjzu.sell.dto;

import com.sdjzu.sell.dataobject.OrderDetail;
import com.sdjzu.sell.dataobject.OrderMaster;
import com.sdjzu.sell.enums.OrderStatusEnum;
import com.sdjzu.sell.enums.PayStatusEnum;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author lychee
 * @date 2020/1/11 21:01
 */
@Getter
@Setter
public class OrderDTO extends OrderMaster {
//    /**
//     * 订单ID
//     */
//    private String orderId;
//
//    /**
//     * 买家名字
//     */
//    private String buyerName;
//
//    /**
//     * 买家手机号
//     */
//    private String buyerPhone;
//
//    /**
//     * 买家地址
//     */
//    private String buyerAddress;
//
//    /**
//     * 买家微信openid
//     */
//    private String buyerOpenid;
//
//    /**
//     * 订单数量
//     */
//    private BigDecimal orderAmount;
//
//    /**
//     * 订单状态 默认为新下单 0新订单，1订单完结，2取消订单
//     */
//    private OrderStatusEnum orderStatus;
//
//    /**
//     * 支付状态，默认为 0等待支付
//     */
//    private PayStatusEnum payStatus;
//
//    /**
//     * 创建时间
//     */
//    private Date createTime;
//
//    /**
//     * 更新时间 自动更新
//     */
//    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}
