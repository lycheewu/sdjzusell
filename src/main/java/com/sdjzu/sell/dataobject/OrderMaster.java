package com.sdjzu.sell.dataobject;

import com.sdjzu.sell.enums.OrderStatusEnum;
import com.sdjzu.sell.enums.PayStatusEnum;
import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @author lychee
 * @date 2020/1/11 17:09
 */
@Data
@Entity
@DynamicUpdate //动态更新时间
@Table(name = "order_master")
public class OrderMaster {

    /**
     * 订单ID
     */
    @Id
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
    private Integer orderStatus= OrderStatusEnum.NEW.getCode();

    /**
     * 支付状态，默认为 0等待支付
     */
    private Integer payStatus= PayStatusEnum.WAIT.getCode();

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间 自动更新
     */
    private Date updateTime;

}
