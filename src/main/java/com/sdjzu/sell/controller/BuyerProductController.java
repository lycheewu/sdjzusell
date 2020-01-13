package com.sdjzu.sell.controller;

import com.sdjzu.sell.VO.ProductInfoVO;
import com.sdjzu.sell.VO.ProductVO;
import com.sdjzu.sell.VO.ResultVO;
import com.sdjzu.sell.dataobject.ProductCategory;
import com.sdjzu.sell.dataobject.ProductInfo;
import com.sdjzu.sell.service.ProductCategoryService;
import com.sdjzu.sell.service.ProductService;
import com.sdjzu.sell.utils.ResultVOUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 买家商品
 *
 * @author lychee
 * @date 2020/1/10 16:44
 */
@RestController
@Api(value = "商品管理")
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Resource
    private ProductService productService;

    @Resource
    private ProductCategoryService productCategoryService;

    @ApiOperation(value = "查询商品")
    @GetMapping(value = "/list")
    public ResultVO list() {
        //1.查询所有的上架的商品
        List<ProductInfo> productInfoList = productService.findUpAll();

        //2.查询类目（一次性查询）

        //传统方法
//        List<Integer> categoryTypeList=new ArrayList<>();
//        for(ProductInfo productInfo:productInfoList){
//            categoryTypeList.add(productInfo.getCategoryType());
//        }

        //精简方法（Java8，lambda）
        List<Integer> categoryTypes = productInfoList.stream().
                map(e -> e.getCategoryType()).collect(Collectors.toList());
        List<ProductCategory> productCategoryList = productCategoryService.findByCategoryTypeIn(categoryTypes);

        //3.数据拼装（另外集成swagger）
        List<ProductVO> productVOS=new ArrayList<>();
        for (ProductCategory productCategory : productCategoryList) {
            ProductVO productVO = new ProductVO();
            productVO.setCategoryType(productCategory.getCategoryType());
            productVO.setCategoryName(productCategory.getCategoryName());


            List<ProductInfoVO> productInfoVOS=new ArrayList<>();
            for (ProductInfo productInfo : productInfoList) {
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())) {
                    ProductInfoVO productInfoVO = new ProductInfoVO();
                    BeanUtils.copyProperties(productInfo, productInfoVO);
                    productInfoVOS.add(productInfoVO);
                }
            }
            productVO.setProductInfoVOS(productInfoVOS);
            productVOS.add(productVO);
        }

        return ResultVOUtil.success(productVOS);
    }
}
