package com.sdjzu.sell.enums;

import lombok.Getter;

/**
 * @author lychee
 * @date 2020/1/11 17:12
 */
@Getter
public enum OrderStatusEnum {

    NEW(0, "新订单"),

    FINISHED(1, "订单完结"),

    CANCEL(2, "取消订单");

    private Integer code;

    private String message;

    OrderStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
