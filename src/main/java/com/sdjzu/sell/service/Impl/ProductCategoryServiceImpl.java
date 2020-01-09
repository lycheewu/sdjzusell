package com.sdjzu.sell.service.Impl;

import com.sdjzu.sell.dataobject.ProductCategory;
import com.sdjzu.sell.repository.ProductCategoryRepository;
import com.sdjzu.sell.service.ProductCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 类目
 * @author lychee
 * @date 2020/1/8 20:44
 */
@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
    @Resource
    private ProductCategoryRepository productCategoryRepo;

    @Override
    public ProductCategory findById(Integer categoryId) {
        return productCategoryRepo.findById(categoryId).orElse(null);
    }

    @Override
    public List<ProductCategory> findAll() {
        return productCategoryRepo.findAll();
    }

    @Override
    public List<ProductCategory> findByCategoryTypeIn(List<Integer> categoryTypeList) {
        return productCategoryRepo.findByCategoryTypeIn(categoryTypeList);
    }

    @Override
    public ProductCategory save(ProductCategory productCategory) {
        return productCategoryRepo.save(productCategory);
    }
}
