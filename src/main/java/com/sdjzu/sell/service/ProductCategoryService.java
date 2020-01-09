package com.sdjzu.sell.service;

import com.sdjzu.sell.dataobject.ProductCategory;

import java.util.List;

/**
 * @author lychee
 * @date 2020/1/8 20:39
 */
public interface ProductCategoryService {

    ProductCategory findById(Integer categoryId);

    List<ProductCategory> findAll();

    List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList);

    ProductCategory save(ProductCategory productCategory);


}
