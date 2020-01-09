package com.sdjzu.sell;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author lychee
 * @date 2020/1/8 11:15
 */

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class LoggerTest {

    @Test
    public void test1() {
        //如何在日志中输出变量
        String name = "sell";
        String password = "123456";
        log.info("name:{},password:{}", name, password);

        log.debug("debug......");
        log.error("error......");
    }
}
