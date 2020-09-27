package com.rice.jedis.test;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author ：by wgz
 * @description：
 * @Date ：Created in 2020/9/27 16:29
 * @Version: $
 */
public class JedisPoolTest {

    private Jedis jedis;

    @Before
    public void init()throws Exception{
        // 0.创建一个配置对象
        JedisPoolConfig config = new JedisPoolConfig();
        // 最大连接数
        config.setMaxTotal(50);
        // 最大空闲连接
        config.setMaxIdle(10);
        // 其他。。。

        // 1.创建Jedis连接池对象
        JedisPool jedisPool = new JedisPool(config, "localhost", 6379);

        // 2.获取连接
        jedis = jedisPool.getResource();
    }

    @After
    public void destroy()throws Exception{
        // 4.归还到连接池中
        jedis.close();;
    }

    @Test
    public void test(){
        // 3. 使用
        jedis.set("Zhangtie","嘿嘿大魔王");

        System.out.println(jedis.get("Zhangtie"));
    }
}
