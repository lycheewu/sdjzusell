package com.sdjzu.sell.dataobject;

import lombok.Data;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 类目表
 *
 * @author lychee
 * @date 2020/1/8 16:49
 */
@Entity
@Data
@DynamicUpdate //动态更新时间
@Table(name = "product_category")
public class ProductCategory {
    /**
     * 类目ID
     */
    @Id
    @GeneratedValue
    private Integer categoryId;

    /**
     * 类目名称
     */
    private String categoryName;

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

//    @Override
//    public String toString() {
//        return "ProductCategory{" +
//                "categoryId=" + categoryId +
//                ", categoryName='" + categoryName + '\'' +
//                ", categoryType=" + categoryType +
//                '}';
//    }

}
