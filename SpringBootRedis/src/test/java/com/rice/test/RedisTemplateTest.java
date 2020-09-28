package com.rice.test;

import com.rice.SpringbootRedisApplication;
import com.rice.uitl.RedisUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.omg.CORBA.DynAnyPackage.Invalid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author ：by wgz
 * @description：
 * @Date ：Created in 2020/9/28 10:00
 * @Version: $
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootRedisApplication.class)
public class RedisTemplateTest {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisUtil redisUtil;

    @Test
    public void testRedis() {
//        Invalid UTF-8 start byte 0x98
//        不是问题
//        System.out.println(redisUtil.get("Zhangtie"));
        System.out.println(stringRedisTemplate.opsForValue().get("Zhangtie"));
    }

    @Test
    public void testRedis2() {
        redisUtil.set("PMZhang", "嘿嘿大魔王");
        System.out.println(redisUtil.get("PMZhang"));
    }

}
