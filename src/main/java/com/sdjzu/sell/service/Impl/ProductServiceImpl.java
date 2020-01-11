package com.sdjzu.sell.service.Impl;

import com.sdjzu.sell.dataobject.ProductInfo;
import com.sdjzu.sell.enums.ProductStatusEnum;
import com.sdjzu.sell.repository.ProductInfoRepository;
import com.sdjzu.sell.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author lychee
 * @date 2020/1/10 11:09
 */
@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductInfoRepository productInfoRepo;

    @Override
    public ProductInfo findById(String productId) {
        return productInfoRepo.findById(productId).orElse(null);
    }

    @Override
    public List<ProductInfo> findUpAll() {
        return productInfoRepo.findByProductStatus(ProductStatusEnum.UP.getCode());
    }

    @Override
    public Page<ProductInfo> findAll(Pageable pageable) {
        return productInfoRepo.findAll(pageable);
    }

    @Override
    public ProductInfo save(ProductInfo productInfo) {
        return productInfoRepo.save(productInfo);
    }
}
