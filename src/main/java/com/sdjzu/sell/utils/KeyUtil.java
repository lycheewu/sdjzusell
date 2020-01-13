package com.sdjzu.sell.utils;

import java.util.Random;

/**
 * mysql数据库的相关工具
 * @author lychee
 * @date 2020/1/12 19:51
 */
public class KeyUtil {

    /**
     * 生成唯一的主键
     * 格式：时间+随机数（6位）
     */
    //避免多线程并发 加上synchronized
    public static synchronized String genUniqueKey(){
        Random random=new Random();

        //时间精确到毫秒
        System.currentTimeMillis();

        //生成六位随机数
        Integer value=random.nextInt(900000)+100000;

        return  System.currentTimeMillis()+String.valueOf(value);
    }
}
