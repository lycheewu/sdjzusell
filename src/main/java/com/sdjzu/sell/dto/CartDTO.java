package com.sdjzu.sell.dto;

import lombok.Data;

/**
 * @author lychee
 * @date 2020/1/12 20:21
 */
@Data
//购物车
public class CartDTO {

    /**
     * 商品ID
     */
    private String productId;

    /**
     * 订单内商品的数量
     */
    private Integer productQuantity;

    public CartDTO(String productId, Integer productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }
}
