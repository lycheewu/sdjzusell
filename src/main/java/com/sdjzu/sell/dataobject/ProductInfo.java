package com.sdjzu.sell.dataobject;

import com.sdjzu.sell.enums.ProductStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 商品
 *
 * @author lychee
 * @date 2020/1/8 21:14
 */
@Entity
@Data
@DynamicUpdate //动态更新时间
@Table(name = "product_info")
public class ProductInfo {

    /**
     * 商品ID
     */
    @Id
    private String productId;

    /**
     * 商品名称
     */
    private String productName;

    /**
     * 商品单价
     */
    private BigDecimal productPrice;

    /**
     * 商品库存
     */
    private Integer productStock;

    /**
     * 商品描述
     */
    private String productDescription;

    /**
     * .小图
     */
    private String productIcon;

    /**
     * .状态，0正常 1下架
     */
    private Integer productStatus=ProductStatusEnum.UP.getCode();

    /**
     * 类目编号
     */
    private Integer categoryType;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间 自动更新
     */
    private Date updateTime;

}
