package com.sdjzu.sell.service.Impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

/**
 * @author lychee
 * @date 2020/1/8 21:01
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Component
@Repository
class ProductCategoryServiceImplTest {

    @Resource
    private ProductCategoryServiceImpl productCategoryService;
    @Test
    void findById() {
    }

    @Test
    void findAll() {
    }

    @Test
    void findByCategoryTypeIn() {
    }

    @Test
    void save() {
    }
}