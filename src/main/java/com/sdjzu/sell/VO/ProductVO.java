package com.sdjzu.sell.VO;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.sdjzu.sell.dataobject.ProductInfo;
import lombok.Data;

import java.util.List;

/**
 * @author lychee
 * @date 2020/1/10 19:29
 */
@Data
public class ProductVO {
    /**
     *类目名字
     */
    @JsonProperty("name")
    private String categoryName;

    /**
     * 类目编号
     */
    @JsonProperty("type")
    private Integer categoryType;

    @JsonProperty("foods")
    private List<ProductInfoVO> productInfoVOS;
}
