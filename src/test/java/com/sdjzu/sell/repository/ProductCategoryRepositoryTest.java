package com.sdjzu.sell.repository;

import com.sdjzu.sell.dataobject.ProductCategory;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * @author lychee
 * @date 2020/1/8 16:58
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Component
@Repository
public class ProductCategoryRepositoryTest {

    @Resource
    private ProductCategoryRepository productCateRepo;

    @Test
    @Transactional //回滚数据库事务，用在测试的时候数据库中不产生改变（完全回滚），用在service层发生错误才出现回滚
    public void findOneTest() {
        ProductCategory productCategory = productCateRepo.findById(1).orElse(null);
        System.out.println(productCategory.toString());
    }
}