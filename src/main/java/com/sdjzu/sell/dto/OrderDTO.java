package com.sdjzu.sell.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.sdjzu.sell.dataobject.OrderDetail;
import com.sdjzu.sell.enums.OrderStatusEnum;
import com.sdjzu.sell.enums.PayStatusEnum;
import com.sdjzu.sell.utils.serializer.Data2LongSerializer;
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
//@JsonInclude(JsonInclude.Include.NON_NULL)//当值为null时不返回
public class OrderDTO {
    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 买家名字
     */
    private String buyerName;

    /**
     * 买家手机号
     */
    private String buyerPhone;

    /**
     * 买家地址
     */
    private String buyerAddress;

    /**
     * 买家微信openid
     */
    private String buyerOpenid;

    /**
     * 订单数量
     */
    private BigDecimal orderAmount;

    /**
     * 订单状态 默认为新下单 0新订单，1订单完结，2取消订单
     */
    private Integer orderStatus;

    /**
     * 支付状态，默认为 0等待支付
     */
    private Integer payStatus;

    /**
     * 创建时间
     */
    @JsonSerialize(using = Data2LongSerializer.class)
    private Date createTime;

    /**
     * 更新时间 自动更新
     */
    @JsonSerialize(using = Data2LongSerializer.class)//将时间从毫秒级转换成秒级
    private Date updateTime;

    private List<OrderDetail> orderDetailList;
}
