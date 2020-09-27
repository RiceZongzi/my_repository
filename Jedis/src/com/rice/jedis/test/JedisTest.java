package com.rice.jedis.test;

import org.junit.Test;
import redis.clients.jedis.Jedis;

/**
 * @Author ：by wgz
 * @description：
 * @Date ：Created in 2020/9/27 14:52
 * @Version: $
 */
public class JedisTest {

    @Test
    public void test1(){
        //1. 获取连接
        Jedis jedis = new Jedis("localhost",6379);
        //2. 操作
        jedis.set("address","Peking");
        //3. 关闭连接
        jedis.close();
    }
}
