package com.sdjzu.sell.VO;

import lombok.Data;

import java.math.BigDecimal;

/**
 * @author lychee
 * @date 2020/1/10 19:52
 */
@Data
public class ProductInfoVO {

    private String productId;

    private String productName;

    private BigDecimal productPrice;

    private String productDescription;

    private String productIcon;
}
