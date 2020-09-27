package com.rice.jedis.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @Author ：by wgz
 * @description：
 * @Date ：Created in 2020/9/27 14:52
 * @Version: $
 */
public class JedisTest {

    private Jedis jedis;

    @Before
    public void init()throws Exception{
        //1. 获取连接
        jedis = new Jedis("localhost",6379);
    }

    @After
    public void destroy()throws Exception{
        //3. 关闭连接
        jedis.close();
    }

    @Test
    public void test1(){
        //2. 操作
        jedis.set("address","Peking");
    }
}
