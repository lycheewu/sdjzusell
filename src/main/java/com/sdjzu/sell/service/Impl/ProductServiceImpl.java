package com.sdjzu.sell.service.Impl;

import com.sdjzu.sell.dataobject.ProductInfo;
import com.sdjzu.sell.dto.CartDTO;
import com.sdjzu.sell.enums.ProductStatusEnum;
import com.sdjzu.sell.enums.ResultEnum;
import com.sdjzu.sell.exception.SellException;
import com.sdjzu.sell.repository.ProductInfoRepository;
import com.sdjzu.sell.service.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Objects;

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

    @Override
    @Transactional
    public void increaseStock(List<CartDTO> cartDTOList) {
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoRepo.findById(cartDTO.getProductId()).orElse(null);
            if (Objects.isNull(productInfo)) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //库存 + 购物车的数量
            Integer result = productInfo.getProductStock() + cartDTO.getProductQuantity();
            //把增加的结果再保存在数据库中
            productInfo.setProductStock(result);
            productInfoRepo.save(productInfo);
        }
    }

    @Override
    @Transactional
    //TODO：项目优化，当卖出去的东西超过库存（超卖）时，使用锁机制
    public void decreaseStock(List<CartDTO> cartDTOList) {
        //遍历购物车
        for (CartDTO cartDTO : cartDTOList) {
            ProductInfo productInfo = productInfoRepo.findById(cartDTO.getProductId()).orElse(null);
            if (Objects.isNull(productInfo)) {
                throw new SellException(ResultEnum.PRODUCT_NOT_EXIST);
            }
            //库存 — 购物车的数量
            Integer result = productInfo.getProductStock() - cartDTO.getProductQuantity();
            if (result < 0) {
                throw new SellException(ResultEnum.PRODUCT_STOCK_ERROR);
            }
            //把减掉的结果再保存在数据库中
            productInfo.setProductStock(result);
            productInfoRepo.save(productInfo);
        }
    }
}
