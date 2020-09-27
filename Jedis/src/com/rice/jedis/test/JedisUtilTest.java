package com.rice.jedis.test;

import com.rice.jedis.utils.JedisPoolUtils;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @Author ：by wgz
 * @description：
 * @Date ：Created in 2020/9/27 16:45
 * @Version: $
 */
public class JedisUtilTest {

    private Jedis jedis;

    @Before
    public void init()throws Exception{
        jedis = JedisPoolUtils.getJedis();
    }

    @After
    public void destroy()throws Exception{
        JedisPoolUtils.close(jedis);
    }

    @Test
    public void test(){
        jedis.set("Zhangtie","嘿嘿大魔王");

        System.out.println(jedis.get("Zhangtie"));
    }
}
