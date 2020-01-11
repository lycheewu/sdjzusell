package com.sdjzu.sell.enums;

import lombok.Getter;

/**
 * 商品状态
 * @author lychee
 * @date 2020/1/10 11:18
 */
@Getter
public enum ProductStatusEnum {
    /**
     * 0是上架
     */
    UP(0,"在上架中"),
    DOWN(1,"已下架");

    private Integer code;

    private String message;

    ProductStatusEnum(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    ProductStatusEnum(Integer code) {
        this.code = code;
    }
}
