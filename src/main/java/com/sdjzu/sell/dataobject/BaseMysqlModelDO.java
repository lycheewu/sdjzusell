package com.sdjzu.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import java.util.Date;

/**
 * 公共基类模型，创建时间和更新时间都包含在内，避免代码冗余
 * @author lychee
 * @date 2020/1/11 20:00
 */
//@Data
//@DynamicUpdate //动态更新时间
//@Entity
public class BaseMysqlModelDO {
    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间 自动更新
     */
    private Date updateTime;
}
